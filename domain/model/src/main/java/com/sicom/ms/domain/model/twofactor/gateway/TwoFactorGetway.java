package com.sicom.ms.domain.model.twofactor.gateway;

import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import reactor.core.publisher.Mono;

public interface TwoFactorGetway {
    Mono<GenerateSecretCodeResponse> generateSecretCode(GenerateSecretCodeRequest request);
}
