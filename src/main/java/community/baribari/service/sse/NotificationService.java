package community.baribari.service.sse;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "notifications")
public class NotificationService {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter createEmitter(Long userId) {
        SseEmitter emitter = new SseEmitter(30 * 60 * 1000L); // 30분 타임아웃
        emitters.put(userId, emitter);

        // 캐시된 알림을 가져와서 전송
        List<String> cachedNotifications = getCachedNotifications(userId);
        for (String notification : cachedNotifications) {
            try {
                emitter.send(SseEmitter.event().data(notification));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(userId);
                break;
            }
        }

        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> {
            emitters.remove(userId);
            emitter.complete();
        });
        emitter.onError((e) -> {
            emitters.remove(userId);
            emitter.completeWithError(e);
        });

        return emitter;
    }

    public void sendNotification(Long userId, String message) {
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(userId);
                cacheNotification(userId, message);
            }
        } else {
            cacheNotification(userId, message);
        }
    }

    @Cacheable(value = "notifications", key = "#userId")
    public List<String> getCachedNotifications(Long userId) {
        return new ArrayList<>();
    }

    @CachePut(value = "notifications", key = "#userId")
    public List<String> cacheNotification(Long userId, String message) {
        List<String> cachedMessages = getCachedNotifications(userId);
        cachedMessages.add(message);
        return cachedMessages;
    }

    @CacheEvict(value = "notifications", key = "#userId")
    public void evictCache(Long userId) {
        // 캐시 삭제
    }
}
