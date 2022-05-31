package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmUserRequest;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmUserRules {
    public static final Rules<ConfirmUserRequest> CONFIRM_USER_REQUEST_RULES = Rules.of(
            cannotBeNull(ConfirmUserRequest::getUser, "ConfirmUserRequest", "user"),
            cannotBeNull(ConfirmUserRequest::getCode, "ConfirmUserRequest", "code")
    );
}