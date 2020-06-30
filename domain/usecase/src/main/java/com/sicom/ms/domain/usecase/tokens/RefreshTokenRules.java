package com.sicom.ms.domain.usecase.tokens;

import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenRules {
    public static final Rules<RefreshToken> REFRESH_TOKEN_RULES = Rules.of(
            cannotBeNull(RefreshToken::getToken, "refreshToken", "Token")
    );
}
