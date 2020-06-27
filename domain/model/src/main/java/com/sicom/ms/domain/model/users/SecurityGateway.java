package com.sicom.ms.domain.model.users;

import reactor.core.publisher.Mono;

public interface SecurityGateway {
    Mono<User> generateToken(User user);
}
