package com.sicom.ms.domain.model.fcm;

import reactor.core.publisher.Mono;

import java.util.List;

public interface NotificationGateway {
    Mono<String> sendPushNotification(Notification requestuserId);
    Mono<String> sendMultiplePushNotifications(List<Notification> notifications);
    Mono<String> saveNotification(Notification request);
    Mono<String> readNotification(String userId, String notificationId);
    Mono<String> saveUserDevice(UserDeviceNotification userDeviceNotification, String userId);
}
