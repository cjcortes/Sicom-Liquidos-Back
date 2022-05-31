package com.sicom.ms.domain.model.twofactor.gateway;

import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import reactor.core.publisher.Mono;

public interface TwoFactorSecretCodeGateway {
    Mono<TwoFactorSecretCode> saveOrUpdate(TwoFactorSecretCode secretCode);

    Mono<TwoFactorSecretCode> findBySecret(String secret);
}
