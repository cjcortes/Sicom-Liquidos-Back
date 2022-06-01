package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorGetway;
import com.sicom.ms.domain.model.users.*;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.users.AutenticacionNSRules.AUTENTICACION_NS_REQUEST_RULES;


@RequiredArgsConstructor
public class AutenticacionNSUseCase {
    private final ObjectValidator objectValidator;
    private final AutenticacionNSGateway autenticacionNSGateway;
    private final SecurityGateway securityGateway;
    private final TwoFactorGetway twoFactorGetway;

    public Mono<User> login(AutenticacionNSRequest request) {
        objectValidator.validate(request, AUTENTICACION_NS_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("encryptPassword");

        return autenticacionNSGateway.login(request)
                .flatMap(securityGateway::generateToken);

    }

    public Mono<GenerateSecretCodeResponse> login2(AutenticacionNSRequest request) {
        objectValidator.validate(request, AUTENTICACION_NS_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("encryptPassword");

       return autenticacionNSGateway.login(request)
                .flatMap(user -> twoFactorGetway.generateSecretCode(GenerateSecretCodeRequest.builder()
                        .user(user.getName())
                        .email("email").build()));

    }


}