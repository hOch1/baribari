package community.baribari.service.sse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationService {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SseEmitter subscribe(Long userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitters.put(userId, emitter);

        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));
        emitter.onError(e -> emitters.remove(userId));


        return emitter;
    }

    public void sendNotification(Long userId, String message, String link) {
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            try {
                Map<String, String> data = Map.of("message", message, "link", link);
                String jsonData = objectMapper.writeValueAsString(data);
                emitter.send(SseEmitter.event().data(jsonData));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(userId); // 전송 실패 시 emitter 제거
            }
        }
    }

    @Scheduled(fixedRate = 30000) // 30초마다 실행
    public void sendKeepAlive() {
        emitters.forEach((userId, emitter) -> {
            try {
                emitter.send(SseEmitter.event().comment("ping"));
            } catch (IOException e) {
                emitter.completeWithError(e);
                emitters.remove(userId);
            }
        });
    }
}
