package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.users.EncryptPasswordRequest;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncryptPasswordRules {
    public static final Rules<EncryptPasswordRequest>  ENCRYPT_PASSWORD_REQUEST_RULES = Rules.of(
            cannotBeNull(EncryptPasswordRequest::getCredencialesBase64, "EncryptPasswordRequest", "data")
    );
}
