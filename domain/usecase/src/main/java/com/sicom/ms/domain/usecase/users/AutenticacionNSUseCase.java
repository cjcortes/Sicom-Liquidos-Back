package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorGetway;
import com.sicom.ms.domain.model.users.AutenticacionNSGateway;
import com.sicom.ms.domain.model.users.AutenticacionNSRequest;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
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

    public Mono<User> login(AutenticacionNSRequest request, boolean twoFactorStatus) {
        objectValidator.validate(request, AUTENTICACION_NS_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("encryptPassword");

        return autenticacionNSGateway.login(request)
                .flatMap(user -> twoFactorStatus
                        ? twoFactorGetway.generateSecretCode(null).thenReturn(user)
                        : securityGateway.generateToken(user))
                .doOnNext(user-> user.toBuilder().twoFactorAuth(twoFactorStatus).build());
    }

//            return autenticacionNSGateway.login(request)
//            .flatMap(user -> twoFactorGetway.confirmSecretCode(ConfirmSecretCodeRequest.builder().user(user.getUser())
//            .code(request.getCode()).build())
//            .flatMap(confirmSecretCodeResponse -> SecretCodeStatusEnum.VALID.name().equals(confirmSecretCodeResponse.getStatus())
//            ? Mono.just(user)
//            : Mono.empty()))
//            .flatMap(securityGateway::generateToken);
}