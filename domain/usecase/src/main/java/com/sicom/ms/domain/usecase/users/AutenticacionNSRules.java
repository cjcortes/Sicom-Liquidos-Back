package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.users.AutenticacionNSRequest;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AutenticacionNSRules {
    public static final Rules<AutenticacionNSRequest>AUTENTICACION_NS_REQUEST_RULES = Rules.of(
            cannotBeNull(AutenticacionNSRequest::getCredenciales, "AutenticacionNSRequest", "data")
    );
}