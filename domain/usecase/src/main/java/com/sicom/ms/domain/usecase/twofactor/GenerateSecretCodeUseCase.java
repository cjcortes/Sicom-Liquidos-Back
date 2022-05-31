package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.MailRequest;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
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

    public Mono<GenerateSecretCodeResponse> generateSecretCode(GenerateSecretCodeRequest request, String mailSubject, String mailBody) {
        objectValidator.validate(request, GENERATE_SECRET_CODE_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("GenerateSecretCodeUseCase");

        //1- Buscar usuario para obtener uuid (semilla)
        return userGateway.findByUser(request.getUser())
                //2- Generar codigo de seguridad
                .flatMap(user -> twoFactorCommon.generateCode()
                        //3- Construir objecto two-factor-secret-code
                        .flatMap(code -> twoFactorCommon.buildTwoFactorSecretCode(user.getUuid(), user.getUser(), code, SecretCodeStatusEnum.SENDING)
                                //4- Guardar o actualizar objecto two-factor-secret-code
                                .flatMap(secretCodeGateway::saveOrUpdate)
                                //5- Enviar codigo a traves de api
                                //ToDO pendiente implementacion sicom-internexa
                                .flatMap(secretCode -> mailGateway.send(MailRequest.builder()
                                                .email(request.getEmail())
                                                .subject(mailSubject)
                                                .body(String.format(mailBody, code))
                                                .build())
                                        .thenReturn(secretCode))))
                //6- Contruir objecto respuesta
                .map(secretCode -> GenerateSecretCodeResponse.builder()
                        .user(secretCode.getUser())
                        .status(secretCode.getStatus())
                        .date(secretCode.getDate())
                        .build());
    }
}
