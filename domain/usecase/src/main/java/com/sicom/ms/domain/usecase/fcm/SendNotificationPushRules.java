package com.sicom.ms.domain.usecase.fcm;

import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.UserDeviceNotification;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendNotificationPushRules {
    public static final Rules<Notification> NOTIFICATION_REQUEST_RULES = Rules.of(
            cannotBeNull(Notification::getTitle, "notification", "title"),
            cannotBeNull(Notification::getBody, "notification", "body"),
            cannotBeNull(Notification::getDueDate, "notification", "dueDate")
    );

    public static final Rules<String> READ_NOTIFICATION_REQUEST_RULES = Rules.of(
            cannotBeNull(value -> value, "notification", "notificationId")
    );

    public static final Rules<UserDeviceNotification> SAVE_USER_DEVICE_NOTIFICATION_REQUEST_RULES = Rules.of(
            cannotBeNull(UserDeviceNotification::getDeviceToken, "notification", "deviceId")
    );
}
