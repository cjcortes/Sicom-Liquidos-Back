package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.users.EncryptPasswordGateway;
import com.sicom.ms.domain.model.users.EncryptPasswordRequest;
import com.sicom.ms.domain.model.users.EncryptedPasswordResponse;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.users.EncryptPasswordRules.ENCRYPT_PASSWORD_REQUEST_RULES;

@RequiredArgsConstructor
public class EncryptPasswordUseCase {
    private final ObjectValidator objectValidator;
    private final EncryptPasswordGateway encryptPasswordGateway;

    public Mono<EncryptedPasswordResponse> encryptPassword(EncryptPasswordRequest request) {
        objectValidator.validate(request, ENCRYPT_PASSWORD_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("encryptPassword");

        return encryptPasswordGateway.encryptPassword(request);
    }

}
