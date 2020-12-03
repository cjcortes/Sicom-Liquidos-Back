package com.sicom.ms.domain.model.fcm;

import reactor.core.publisher.Mono;

public interface NotificationGateway {
    Mono<String> sendPushNotification(Notification request);
}
