package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.RegisterNotificacionOAuth2Request;
import com.sicom.ms.domain.model.twofactor.RegisterNotificacionOAuth2Response;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorNotificationGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.twofactor.RegisterNotificationOAuth2WebAppRules.REGISTER_NOTIFICATION_OAUTH2_WEBAPP_REQUEST_RULES;

@RequiredArgsConstructor
public class RegisterNotificationOAuth2WebAppUseCase {
    private final ObjectValidator objectValidator;
    private final TwoFactorNotificationGateway twoFactorNotificationGateway;

    public Mono<RegisterNotificacionOAuth2Response> registerNotificationOAuth2WebApp(RegisterNotificacionOAuth2Request request) {
        objectValidator.validate(request, REGISTER_NOTIFICATION_OAUTH2_WEBAPP_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("RegisterNotificationOAuth2WebAppUseCase");

        return twoFactorNotificationGateway.saveOrUpdate(request.getUser(), request.getSicomCode())
                .thenReturn(RegisterNotificacionOAuth2Response.builder().success(true).build());
    }
}
