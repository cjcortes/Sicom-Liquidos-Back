package com.sicom.ms.domain.usecase.dashboardgases.authorizevehicles;

import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicleFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAuthorizeVehicleByFiltersRules {

    public static final Rules<AuthorizeVehicleFilters> GET_AUTHORIZE_VEHICLE_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(AuthorizeVehicleFilters::getCertifierSicomCode, "authorizeVehicleFilters", "certifierSicomCode"),
            Rule.of("authorizeVehicleFilters.certifierSicomCodeCanNotBeEmpty",
                    "you must send certifier sicom code",
                    object -> (!object.getCertifierSicomCode().equals("-1") || object.getCertifierSicomCode().equals(null)))
    );
}
