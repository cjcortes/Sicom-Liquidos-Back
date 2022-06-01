package com.sicom.ms.infrastructure.web.twofactor;

import com.sicom.ms.domain.model.twofactor.*;
import com.sicom.ms.domain.usecase.twofactor.ConfirmSecretCodeUseCase;
import com.sicom.ms.domain.usecase.twofactor.ConfirmUserUseCase;
import com.sicom.ms.domain.usecase.twofactor.GenerateSecretCodeUseCase;
import com.sicom.ms.domain.usecase.twofactor.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/two-factor")
@RequiredArgsConstructor
public class TwoFactorController {
    private final RegisterUserUseCase registerUserUseCase;
    private final ConfirmUserUseCase confirmUserUseCase;
    private final GenerateSecretCodeUseCase generateSecretCodeUseCase;
    private final ConfirmSecretCodeUseCase confirmSecretCodeUseCase;

    @Value("${app.two-factor.mail.subject}")
    private String mailSubject;

    @Value("${app.two-factor.mail.body}")
    private String mailBody;

    @PostMapping("/register-user")
    public Mono<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest request) {
        return registerUserUseCase.registerUser(request, mailSubject, mailBody);
    }

    @PostMapping("/confirm-user")
    public Mono<ConfirmUserResponse> confirmUser(@RequestBody ConfirmUserRequest request) {
        return confirmUserUseCase.confirmUser(request);
    }

    @PostMapping("/generate-secret-code")
    public Mono<GenerateSecretCodeResponse> generateSecretCode(@RequestBody GenerateSecretCodeRequest request) {
        return generateSecretCodeUseCase.generateSecretCode(request, mailSubject, mailBody);
    }

    @PostMapping("/confirm-secret-code")
    public Mono<ConfirmSecretCodeResponse> confirmSecretCode(@RequestBody ConfirmSecretCodeRequest request) {
        return confirmSecretCodeUseCase.confirmSecretCode(request);
    }
}