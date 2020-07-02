package com.sicom.ms.domain.usecase.tokens;

import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.tokens.RefreshTokenRules.REFRESH_TOKEN_RULES;

@RequiredArgsConstructor
public class RefreshTokenUseCase {

    private final ObjectValidator objectValidator;
    private final SecurityGateway securityGateway;

    public Mono<RefreshToken> refresh(RefreshToken refreshToken) {
        objectValidator.validate(refreshToken, REFRESH_TOKEN_RULES)
                .throwBadRequestExceptionIfInvalid("login");

        return Mono.just(refreshToken).flatMap(securityGateway::refreshToken);
    }
}
