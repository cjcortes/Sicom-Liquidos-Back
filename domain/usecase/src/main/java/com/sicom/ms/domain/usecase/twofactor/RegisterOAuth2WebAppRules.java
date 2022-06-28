package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Request;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterOAuth2WebAppRules {
    public static final Rules<RegisterOAuth2Request> REGISTER_OAUTH2_WEBAPP_REQUEST_RULES = Rules.of(
            cannotBeNull(RegisterOAuth2Request::getSicomCode, "RegisterOAuth2Request", "sicomCode"),
            cannotBeNull(RegisterOAuth2Request::getResult, "RegisterOAuth2Request", "result"),
            cannotBeNull(RegisterOAuth2Request::getDeviceId, "RegisterOAuth2Request", "deviceId")
    );
}
