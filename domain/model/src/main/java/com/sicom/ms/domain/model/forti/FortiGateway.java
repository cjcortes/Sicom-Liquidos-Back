package com.sicom.ms.domain.model.forti;

import reactor.core.publisher.Mono;

public interface FortiGateway {
    Mono<FortiUser> searchUser(int userId);
    Mono<String> validateToken(ValidateTokenRequest validateTokenRequest);
}
