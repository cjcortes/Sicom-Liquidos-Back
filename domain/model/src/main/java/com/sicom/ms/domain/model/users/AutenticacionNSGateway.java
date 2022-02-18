package com.sicom.ms.domain.model.users;

import reactor.core.publisher.Mono;

public interface AutenticacionNSGateway {
    Mono<User> login(AutenticacionNSRequest request);
}
