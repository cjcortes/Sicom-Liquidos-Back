package com.sicom.ms.infrastructure.web.security;

import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.users.AutenticacionNSRequest;
import com.sicom.ms.domain.model.users.EncryptPasswordRequest;
import com.sicom.ms.domain.model.users.EncryptedPasswordResponse;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.usecase.tokens.RefreshTokenUseCase;
import com.sicom.ms.domain.usecase.users.AutenticacionNSUseCase;
import com.sicom.ms.domain.usecase.users.EncryptPasswordUseCase;
import com.sicom.ms.domain.usecase.users.LoginUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/security")
@RequiredArgsConstructor
public class SecurityController {

    @Value("${app.two-factor.status}")
    private boolean twoFactorStatus;

    private final LoginUserUseCase loginUserUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final EncryptPasswordUseCase encryptPasswordUseCase;
    private final AutenticacionNSUseCase autenticacionNSUseCase;

    @PostMapping("/login")
    public Mono<User> login(@RequestBody LoginRequest loginRequest) {
        return loginUserUseCase.login(loginRequest);
    }

    @PostMapping("/refresh-token")
    public Mono<RefreshToken> refreshToken(@RequestBody RefreshToken refreshToken) {
        return refreshTokenUseCase.refresh(refreshToken);
    }

    @PostMapping("/login-encrypt-password")
    public Mono<EncryptedPasswordResponse> encryptPassword(@RequestBody EncryptPasswordRequest request) {
        return encryptPasswordUseCase.encryptPassword(request);
    }

    @PostMapping("/loginns")
    public Mono<User> autenticacionns(@RequestBody AutenticacionNSRequest request) {
        return autenticacionNSUseCase.login(request, twoFactorStatus);
    }

    @PostMapping("/login-two-factor")
    public Mono<User> loginTwoFactor(@RequestBody User request,
                                     @RequestParam String code) {
        return autenticacionNSUseCase.loginTwoFactor(request, code);
    }
}