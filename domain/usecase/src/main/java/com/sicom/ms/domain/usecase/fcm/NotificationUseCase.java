package com.sicom.ms.domain.usecase.fcm;

import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.NotificationGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.fcm.SendNotificationPushRules.NOTIFICATION_REQUEST_RULES;
import static com.sicom.ms.domain.usecase.fcm.SendNotificationPushRules.READ_NOTIFICATION_REQUEST_RULES;

@RequiredArgsConstructor
public class NotificationUseCase {
    private final ObjectValidator objectValidator;
    private final NotificationGateway notificationGateway;

    public Mono<String> save(Notification request) {
        objectValidator.validate(request, NOTIFICATION_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("save");
        return notificationGateway.saveNotification(request);
    }

    public Mono<String> read(String userId, String notificationId) {
        objectValidator.validate(notificationId, READ_NOTIFICATION_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("read");
        return notificationGateway.readNotification(userId, notificationId);
    }
}
