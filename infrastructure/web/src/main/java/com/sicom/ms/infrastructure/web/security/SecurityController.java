package com.sicom.ms.infrastructure.web.security;

import com.sicom.ms.domain.model.forti.ValidateTokenRequest;
import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.users.*;
import com.sicom.ms.domain.usecase.forti.FortiUseCase;
import com.sicom.ms.domain.usecase.tokens.RefreshTokenUseCase;
import com.sicom.ms.domain.usecase.users.AutenticacionNSUseCase;
import com.sicom.ms.domain.usecase.users.EncryptPasswordUseCase;
import com.sicom.ms.domain.usecase.users.LoginUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/security")
@RequiredArgsConstructor
public class SecurityController {
    @Value("${app.forti.status}")
    private boolean fortiStatus;

    private final LoginUserUseCase loginUserUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final FortiUseCase fortiUseCase;
    private final EncryptPasswordUseCase encryptPasswordUseCase;
    private final AutenticacionNSUseCase autenticacionNSUseCase;

    @PostMapping("/login")
    public Mono<User> login(@RequestBody LoginRequest loginRequest) {
        return loginUserUseCase.login(loginRequest, fortiStatus);
    }

    @PostMapping("/refresh-token")
    public Mono<RefreshToken> refreshToken(@RequestBody RefreshToken refreshToken) {
        return refreshTokenUseCase.refresh(refreshToken);
    }

    @PostMapping("/validate-forti-token")
    public Mono<String> validateFortiToken(@RequestBody ValidateTokenRequest validateTokenRequest) {
        return fortiUseCase.validateToken(validateTokenRequest);
    }

    @PostMapping("/login-encrypt-password")
    public Mono<EncryptedPasswordResponse> encryptPassword(@RequestBody EncryptPasswordRequest request) {
        return encryptPasswordUseCase.encryptPassword(request);
    }

    @PostMapping("/loginns")
    public Mono<User> autenticacionns(@RequestBody AutenticacionNSRequest request) {
        return autenticacionNSUseCase.login(request);
    }
    @PostMapping("/login2")
    public Mono<GenerateSecretCodeResponse> login(@RequestBody AutenticacionNSRequest request) {
        return autenticacionNSUseCase.login2(request);
    }
}