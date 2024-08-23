package community.baribari.controller.sse;

import community.baribari.service.sse.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping(value = "/notification", produces = "text/event-stream")
    public SseEmitter streamNotifications(@RequestParam("id") Long userId) {
        return notificationService.createEmitter(userId);
    }
}