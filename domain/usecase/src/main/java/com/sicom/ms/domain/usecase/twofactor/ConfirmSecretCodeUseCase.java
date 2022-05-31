package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorUserGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;

import static com.sicom.ms.domain.usecase.twofactor.ConfirmSecretCodeRules.CONFIRM_SECRET_CODE_REQUEST_RULES;

@RequiredArgsConstructor
public class ConfirmSecretCodeUseCase {
    private final ObjectValidator objectValidator;
    private final TwoFactorCommon twoFactorCommon;
    private final TwoFactorUserGateway userGateway;
    private final TwoFactorSecretCodeGateway secretCodeGateway;

    public Mono<ConfirmSecretCodeResponse> confirmSecretCode(ConfirmSecretCodeRequest request) {
        objectValidator.validate(request, CONFIRM_SECRET_CODE_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("ConfirmSecretCodeUseCase");

        //1- Buscar usuario para obtener uuid (semilla)
        return userGateway.findBy(request.getUser(), UserStatusEnum.ACTIVE)
                //2- Generar secret de los datos ingresados (request)
                .flatMap(user -> twoFactorCommon.generateSecret(user.getUuid(), request.getUser(), request.getCode())
                        //3- Buscar two-factor-secret-code con el secret generado
                        .flatMap(secretCodeGateway::findBySecret)
                        //4- Actualizar objecto two-factor-secret-code cambio de estado y fecha
                        .map(secretCode -> secretCode.toBuilder().status(SecretCodeStatusEnum.VALID.toString()).date(Date.from(Instant.now())).build())
                        //5- Actualizar objecto two-factor-secret-code
                        .flatMap(secretCodeGateway::saveOrUpdate))
                //6- Contruir objecto respuesta
                .map(secretCode -> ConfirmSecretCodeResponse.builder()
                        .user(secretCode.getUser())
                        .status(secretCode.getStatus())
                        .date(secretCode.getDate())
                        .build());
    }
}
