package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.MailRequest;
import com.sicom.ms.domain.model.twofactor.RegisterUserRequest;
import com.sicom.ms.domain.model.twofactor.RegisterUserResponse;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.gateway.MailGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorUserGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.twofactor.RegisterUserRules.REGISTER_USER_REQUEST_RULES;

@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final ObjectValidator objectValidator;
    private final TwoFactorCommon twoFactorCommon;
    private final TwoFactorUserGateway userGateway;
    private final TwoFactorSecretCodeGateway secretCodeGateway;
    private final MailGateway mailGateway;

    public Mono<RegisterUserResponse> registerUser(RegisterUserRequest request,
                                                   String mailSubject,
                                                   String mailBody) {
        objectValidator.validate(request, REGISTER_USER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("RegisterUserUseCase");

        //1- Construir objecto two-factor-user
        return twoFactorCommon.buildTwoFactorUser(request.getUser())
                //2- Guardar o actualizar objecto two-factor-user
                .flatMap(userGateway::saveOrUpdate)
                //3- Generar codigo de seguridad
                .flatMap(user -> twoFactorCommon.generateCode()
                        //4- Construir objecto two-factor-secret-code
                        .flatMap(code -> twoFactorCommon.buildTwoFactorSecretCode(user.getUuid(), user.getUser(), code, SecretCodeStatusEnum.SENDING)
                                //5- Guardar o actualizar objecto two-factor-secret-code
                                .flatMap(secretCodeGateway::saveOrUpdate)
                                //6- Enviar codigo a traves de api
                                //ToDO pendiente implementacion sicom-internexa
                                .flatMap(secretCode -> mailGateway.send(MailRequest.builder()
                                        .email(request.getEmail())
                                        .subject(mailSubject)
                                        .body(String.format(mailBody, code))
                                        .build()))
                                .thenReturn(user)))
                //7- Contruir objecto respuesta
                .map(user -> RegisterUserResponse.builder()
                        .user(user.getUser())
                        .date(user.getDate())
                        .build());
    }
}
