package com.sicom.ms.infrastructure.web.fcm;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.UserDeviceNotification;
import com.sicom.ms.domain.usecase.fcm.NotificationUseCase;
import com.sicom.ms.domain.usecase.fcm.SendPushNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static com.sicom.ms.domain.model.common.Constants.CODE;
import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FCMController {

    private final SendPushNotificationUseCase sendPushNotificationUseCase;
    private final NotificationUseCase notificationUseCase;
    private final AuthenticationGateway authenticationGateway;

    @PostMapping
    public Mono<String> sendPushNotification(@RequestBody Notification notification) {
        notificationUseCase.save(notification);
        return sendPushNotificationUseCase.send(notification);
    }

    @PostMapping(value = "custom")
    public Mono<String> sendCustomPushNotification(@RequestBody List<Notification> notifications) {
        sendPushNotificationUseCase.sendMultiple(notifications);

        notifications.forEach(notification -> {
            notificationUseCase.save(notification);
        });



        return Mono.just("OK");
    }

    @PutMapping(value = "/{notificationId}")
    public Mono<String> readNotification(@PathVariable(value = "notificationId") String notificationId, Principal principal) {
        return authenticationGateway.getClaims(principal)
                .flatMap(claims -> notificationUseCase.read((String) claims.get(SICOM_AGENT), notificationId));
    }

    @PutMapping(value = "registerDevice/{userId}")
    public Mono<String> readNotification(@PathVariable(value = "userId") String userId,
                                         @RequestBody UserDeviceNotification userDeviceNotification,
                                         Principal principal) {
        return notificationUseCase.saveUserDevice(userDeviceNotification,userId);
    }
}
