package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmSecretCodeRules {
    public static final Rules<ConfirmSecretCodeRequest> CONFIRM_SECRET_CODE_REQUEST_RULES = Rules.of(
            cannotBeNull(ConfirmSecretCodeRequest::getUser, "ConfirmSecretCodeRequest", "user"),
            cannotBeNull(ConfirmSecretCodeRequest::getCode, "ConfirmSecretCodeRequest", "code")
    );
}