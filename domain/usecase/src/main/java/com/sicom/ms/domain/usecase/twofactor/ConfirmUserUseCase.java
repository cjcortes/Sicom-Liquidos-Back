package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmUserRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmUserResponse;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorUserGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;

import static com.sicom.ms.domain.usecase.twofactor.ConfirmUserRules.CONFIRM_USER_REQUEST_RULES;

@RequiredArgsConstructor
public class ConfirmUserUseCase {
    private final ObjectValidator objectValidator;
    private final TwoFactorCommon twoFactorCommon;
    private final TwoFactorUserGateway userGateway;
    private final TwoFactorSecretCodeGateway secretCodeGateway;

    public Mono<ConfirmUserResponse> confirmUser(ConfirmUserRequest request) {
        objectValidator.validate(request, CONFIRM_USER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("ConfirmUserUseCase");

        //1- Buscar usuario para obtener uuid (semilla)
        return userGateway.findBy(request.getUser(), UserStatusEnum.PENDING)
                //2- Generar secret de los datos ingresados (request)
                .flatMap(user -> twoFactorCommon.generateSecret(user.getUuid(), request.getUser(), request.getCode())
                        //3- Buscar two-factor-secret-code con el secret generado
                        .flatMap(secretCodeGateway::findBySecret)
                        //4- Actualizar objecto two-factor-secret-code cambio de estado y fecha
                        .map(secretCode -> secretCode.toBuilder().status(SecretCodeStatusEnum.VALID.name()).date(Date.from(Instant.now())).build())
                        //5- Actualizar objecto two-factor-secret-code
                        .flatMap(secretCodeGateway::saveOrUpdate)
                        .thenReturn(user))
                //6- Actualizaro objecto two-factor-user cambio de estado y fecha
                .map(user -> user.toBuilder().status(UserStatusEnum.ACTIVE.name()).date(Date.from(Instant.now())).build())
                //7- Actualizar objecto two-factor-user
                .flatMap(userGateway::saveOrUpdate)
                //8- Contruir objecto respuesta
                .map(user -> ConfirmUserResponse.builder()
                        .user(user.getUser())
                        .status(user.getStatus())
                        .date(user.getDate())
                        .build());
    }
}
