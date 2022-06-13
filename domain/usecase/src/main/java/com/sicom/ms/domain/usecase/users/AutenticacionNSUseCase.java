package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.agents.AgentsGateway;
import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.ApplicationException;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorGateway;
import com.sicom.ms.domain.model.users.AutenticacionNSGateway;
import com.sicom.ms.domain.model.users.AutenticacionNSRequest;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Set;

import static com.sicom.ms.domain.usecase.users.AutenticacionNSRules.AUTENTICACION_NS_REQUEST_RULES;


@RequiredArgsConstructor
public class AutenticacionNSUseCase {
    private final ObjectValidator objectValidator;
    private final AutenticacionNSGateway autenticacionNSGateway;
    private final SecurityGateway securityGateway;
    private final TwoFactorGateway twoFactorGetway;
    private final AgentsGateway agentsGateway;

    public Mono<User> login(AutenticacionNSRequest request, boolean twoFactorStatus) {
        objectValidator.validate(request, AUTENTICACION_NS_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("encryptPassword");

        return autenticacionNSGateway.login(request)
                .map(user -> user.toBuilder().twoFactorAuth(twoFactorStatus).build())
                .flatMap(user -> twoFactorStatus
                        ? agentsGateway.getAgentById(String.valueOf(user.getCode()))
                        .next()
                        .flatMap(agent -> twoFactorGetway.generateSecretCode(GenerateSecretCodeRequest.builder()
                                .user(user.getUser()).email(agent.getEmail())
                                .build())).thenReturn(user)
                        : securityGateway.generateToken(user));
    }

    public Mono<User> loginTwoFactor(User request, String code) {
        return twoFactorGetway.confirmSecretCode(ConfirmSecretCodeRequest.builder().user(request.getUser()).code(code).build())
                .flatMap(confirmSecretCodeResponse -> SecretCodeStatusEnum.VALID.name().equals(confirmSecretCodeResponse.getStatus())
                        ? Mono.just(request)
                        : Mono.error(new ApplicationException("error", "error", (Set<ApplicationErrorDetail>) null)))
                .flatMap(securityGateway::generateToken);
    }
}