package com.sicom.ms.infrastructure.web.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.usecase.twofactor.ConfirmSecretCodeUseCase;
import com.sicom.ms.domain.usecase.twofactor.GenerateSecretCodeUseCase;
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

    @PostMapping("/generate-secret-code")
    public Mono<GenerateSecretCodeResponse> generateSecretCode(@RequestBody GenerateSecretCodeRequest request) {
        return generateSecretCodeUseCase.generateSecretCode(request);
    }

    @PostMapping("/confirm-secret-code")
    public Mono<ConfirmSecretCodeResponse> confirmSecretCode(@RequestBody ConfirmSecretCodeRequest request) {
        return confirmSecretCodeUseCase.confirmSecretCode(request);
    }
}