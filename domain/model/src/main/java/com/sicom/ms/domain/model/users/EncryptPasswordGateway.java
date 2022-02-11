package com.sicom.ms.domain.model.users;

import reactor.core.publisher.Mono;

public interface EncryptPasswordGateway {
    Mono<EncryptedPasswordResponse> encryptPassword(EncryptPasswordRequest request);
}
