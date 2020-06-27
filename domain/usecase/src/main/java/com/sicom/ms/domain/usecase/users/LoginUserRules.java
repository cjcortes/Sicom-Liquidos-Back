package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import com.sicom.ms.domain.usecase.validations.common.NumberRulesFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;
import static com.sicom.ms.domain.usecase.validations.common.NumberRulesFactory.cannotBeNegative;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserRules {
    public static final Rules<LoginRequest> LOGIN_REQUEST_RULES = Rules.of(
            cannotBeNull(LoginRequest::getUser, "loginRequest", "User"),
            cannotBeNull(LoginRequest::getPassword, "loginRequest", "Password")
    );
}
