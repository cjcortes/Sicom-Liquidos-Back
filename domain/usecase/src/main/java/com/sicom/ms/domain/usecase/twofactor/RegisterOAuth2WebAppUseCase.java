package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.error.Error;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2PayloadResponse;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Request;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Response;
import com.sicom.ms.domain.model.twofactor.gateway.OAuth2Gateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorNotificationGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.twofactor.RegisterOAuth2WebAppRules.REGISTER_OAUTH2_WEBAPP_REQUEST_RULES;

@RequiredArgsConstructor
public class RegisterOAuth2WebAppUseCase {
    private final ObjectValidator objectValidator;
    private final TwoFactorNotificationGateway twoFactorNotificationGateway;
    private final OAuth2Gateway oAuth2Gateway;

    public Mono<RegisterOAuth2Response> registerOAuth2WebApp(RegisterOAuth2Request request) {
        objectValidator.validate(request, REGISTER_OAUTH2_WEBAPP_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("RegisterOAuth2WebAppUseCase");

        return twoFactorNotificationGateway.findBySicomCode(request.getSicomCode())
                .flatMap(notification -> oAuth2Gateway.registerOAuth2(request))
                .defaultIfEmpty(RegisterOAuth2Response.builder()
                        .success(false)
                        .error(new Error())
                        .payload(RegisterOAuth2PayloadResponse.builder().date("").information("").build())
                        .build());
    }
}
