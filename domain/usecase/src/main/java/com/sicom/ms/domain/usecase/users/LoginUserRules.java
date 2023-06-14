package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.users.LoginRequest;
<<<<<<< HEAD
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import com.sicom.ms.domain.usecase.validations.common.NumberRulesFactory;
=======
import com.sicom.ms.domain.usecase.validations.Rules;
>>>>>>> release
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;
<<<<<<< HEAD
import static com.sicom.ms.domain.usecase.validations.common.NumberRulesFactory.cannotBeNegative;
=======
>>>>>>> release

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserRules {
    public static final Rules<LoginRequest> LOGIN_REQUEST_RULES = Rules.of(
<<<<<<< HEAD
            cannotBeNull(LoginRequest::getUser, "loginRequest", "User"),
            cannotBeNull(LoginRequest::getPassword, "loginRequest", "Password")
=======
            cannotBeNull(LoginRequest::getUser, "loginRequest", "user"),
            cannotBeNull(LoginRequest::getPassword, "loginRequest", "password")
>>>>>>> release
    );
}
