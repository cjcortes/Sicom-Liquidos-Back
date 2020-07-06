package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserRules {
    public static final Rules<LoginRequest> LOGIN_REQUEST_RULES = Rules.of(
            cannotBeNull(LoginRequest::getUser, "loginRequest", "user"),
            cannotBeNull(LoginRequest::getPassword, "loginRequest", "password")
    );
}
