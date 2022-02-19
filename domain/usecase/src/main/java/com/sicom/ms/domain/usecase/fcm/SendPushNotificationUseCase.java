package com.sicom.ms.domain.usecase.fcm;

import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.NotificationGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.sicom.ms.domain.usecase.fcm.SendNotificationPushRules.NOTIFICATION_REQUEST_RULES;

@RequiredArgsConstructor
public class SendPushNotificationUseCase {

    private final ObjectValidator objectValidator;
    private final NotificationGateway notificationGateway;

    public Mono<String> send(Notification request) {
        objectValidator.validate(request, NOTIFICATION_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("send");
        return notificationGateway.sendPushNotification(request);
    }

    public Mono<String> sendMultiple(List<Notification> notifications) {
        return notificationGateway.sendMultiplePushNotifications(notifications);
    }
}
