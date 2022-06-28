package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.MailRequest;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.gateway.MailGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorUserGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.twofactor.GenerateSecretCodeRules.GENERATE_SECRET_CODE_REQUEST_RULES;

@RequiredArgsConstructor
public class GenerateSecretCodeUseCase {
    private final ObjectValidator objectValidator;
    private final TwoFactorCommon twoFactorCommon;
    private final TwoFactorUserGateway userGateway;
    private final TwoFactorSecretCodeGateway secretCodeGateway;
    private final MailGateway mailGateway;

    public Mono<GenerateSecretCodeResponse> generateSecretCode(GenerateSecretCodeRequest request) {
        objectValidator.validate(request, GENERATE_SECRET_CODE_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("GenerateSecretCodeUseCase");

        //1- Buscar usuario para obtener uuid (semilla)
        return userGateway.findByUser(request.getUser())
                //2- Si no existe se guarda el usuario
                .switchIfEmpty(twoFactorCommon.buildTwoFactorUser(request.getUser()).flatMap(userGateway::saveOrUpdate))
                //3- Generar codigo de seguridad
                .flatMap(user -> twoFactorCommon.generateCode()
                        //4- Construir objecto two-factor-secret-code
                        .flatMap(code -> twoFactorCommon.buildTwoFactorSecretCode(user.getUuid(), user.getUser(), code, SecretCodeStatusEnum.SENDING)
                                //5- Guardar o actualizar objecto two-factor-secret-code
                                .flatMap(secretCodeGateway::saveOrUpdate)
                                //6- Enviar codigo a traves de api
                                .flatMap(secretCode -> mailGateway.send(MailRequest.builder().to(request.getEmail()).build(), code)
                                        .thenReturn(secretCode))))
                //7- Contruir objecto respuesta
                .map(secretCode -> GenerateSecretCodeResponse.builder()
                        .user(secretCode.getUser())
                        .status(secretCode.getStatus())
                        .date(secretCode.getDate())
                        .build());
    }
}
