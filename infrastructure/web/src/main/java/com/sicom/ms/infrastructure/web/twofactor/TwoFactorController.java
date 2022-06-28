package com.sicom.ms.infrastructure.web.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.RegisterNotificacionOAuth2Request;
import com.sicom.ms.domain.model.twofactor.RegisterNotificacionOAuth2Response;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Request;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Response;
import com.sicom.ms.domain.usecase.twofactor.ConfirmSecretCodeUseCase;
import com.sicom.ms.domain.usecase.twofactor.GenerateSecretCodeUseCase;
import com.sicom.ms.domain.usecase.twofactor.RegisterNotificationOAuth2WebAppUseCase;
import com.sicom.ms.domain.usecase.twofactor.RegisterOAuth2WebAppUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/two-factor")
@RequiredArgsConstructor
public class TwoFactorController {
    private final GenerateSecretCodeUseCase generateSecretCodeUseCase;
    private final ConfirmSecretCodeUseCase confirmSecretCodeUseCase;
    private final RegisterNotificationOAuth2WebAppUseCase registerNotificationOauth2UseCase;
    private final RegisterOAuth2WebAppUseCase registerOAuth2UseCase;

    @PostMapping("/generate-secret-code")
    public Mono<GenerateSecretCodeResponse> generateSecretCode(@RequestBody GenerateSecretCodeRequest request) {
        return generateSecretCodeUseCase.generateSecretCode(request);
    }

    @PostMapping("/confirm-secret-code")
    public Mono<ConfirmSecretCodeResponse> confirmSecretCode(@RequestBody ConfirmSecretCodeRequest request) {
        return confirmSecretCodeUseCase.confirmSecretCode(request);
    }

    @PostMapping("/register-notification-oauth2-webapp")
    public Mono<RegisterNotificacionOAuth2Response> registerNotificationOAuth2WebApp(@RequestBody RegisterNotificacionOAuth2Request request) {
        return registerNotificationOauth2UseCase.registerNotificationOAuth2WebApp(request);
    }

    @PostMapping("/register-oauth2-webapp")
    public Mono<RegisterOAuth2Response> registerOAuth2WebApp(@RequestBody RegisterOAuth2Request request) {
        return registerOAuth2UseCase.registerOAuth2WebApp(request);
    }
}