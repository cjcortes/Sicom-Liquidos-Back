package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.RegisterUserRequest;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterUserRules {
    public static final Rules<RegisterUserRequest> REGISTER_USER_REQUEST_RULES = Rules.of(
            cannotBeNull(RegisterUserRequest::getUser, "RegisterUserRequest", "user"),
            cannotBeNull(RegisterUserRequest::getEmail, "RegisterUserRequest", "email")
    );
}