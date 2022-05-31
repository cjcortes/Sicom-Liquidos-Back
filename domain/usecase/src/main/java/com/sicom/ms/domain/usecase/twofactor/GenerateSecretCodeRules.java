package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateSecretCodeRules {
    public static final Rules<GenerateSecretCodeRequest> GENERATE_SECRET_CODE_REQUEST_RULES = Rules.of(
            cannotBeNull(GenerateSecretCodeRequest::getUser, "GenerateSecretCodeRequest", "user"),
            cannotBeNull(GenerateSecretCodeRequest::getEmail, "GenerateSecretCodeRequest", "email")
    );
}