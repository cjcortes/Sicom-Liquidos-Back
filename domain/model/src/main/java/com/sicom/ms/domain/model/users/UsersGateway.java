package com.sicom.ms.domain.model.users;

import reactor.core.publisher.Mono;

public interface UsersGateway {
    Mono<User> login(LoginRequest request);
}
