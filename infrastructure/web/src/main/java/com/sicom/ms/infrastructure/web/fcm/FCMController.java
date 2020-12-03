package com.sicom.ms.infrastructure.web.fcm;

import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.usecase.fcm.SendPushNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FCMController {

    private final SendPushNotificationUseCase sendPushNotificationUseCase;

    @PostMapping
    public Mono<String> sendPushNotification(@RequestBody Notification notification) {
        return sendPushNotificationUseCase.send(notification);
    }

}
