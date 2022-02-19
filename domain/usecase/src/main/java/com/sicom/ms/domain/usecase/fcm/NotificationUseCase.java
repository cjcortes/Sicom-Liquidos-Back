package com.sicom.ms.domain.usecase.fcm;

import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.NotificationGateway;
import com.sicom.ms.domain.model.fcm.UserDeviceNotification;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.fcm.SendNotificationPushRules.*;

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

    public Mono<String> saveUserDevice(UserDeviceNotification userDeviceNotification, String userId) {
        objectValidator.validate(userDeviceNotification, SAVE_USER_DEVICE_NOTIFICATION_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("saveUserDevice");
        return notificationGateway.saveUserDevice(userDeviceNotification, userId);
    }
}
