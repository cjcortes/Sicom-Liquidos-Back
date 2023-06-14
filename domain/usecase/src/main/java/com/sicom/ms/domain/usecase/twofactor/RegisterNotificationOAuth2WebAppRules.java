package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.RegisterNotificacionOAuth2Request;
import com.sicom.ms.domain.usecase.validations.Rules;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

public class RegisterNotificationOAuth2WebAppRules {

    public static final Rules<RegisterNotificacionOAuth2Request> REGISTER_NOTIFICATION_OAUTH2_WEBAPP_REQUEST_RULES = Rules.of(
            cannotBeNull(RegisterNotificacionOAuth2Request::getUser, "RegisterOAuth2Request", "user"),
            cannotBeNull(RegisterNotificacionOAuth2Request::getSicomCode, "RegisterOAuth2Request", "sicomCode")
    );
}
