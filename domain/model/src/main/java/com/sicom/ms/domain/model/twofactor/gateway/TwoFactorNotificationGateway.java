package com.sicom.ms.domain.model.twofactor.gateway;

import com.sicom.ms.domain.model.twofactor.TwoFactorNotification;
import reactor.core.publisher.Mono;

public interface TwoFactorNotificationGateway {
    Mono<TwoFactorNotification> saveOrUpdate(String user, String sicomCode);

    Mono<TwoFactorNotification> findBySicomCode(String sicomCode);
}
