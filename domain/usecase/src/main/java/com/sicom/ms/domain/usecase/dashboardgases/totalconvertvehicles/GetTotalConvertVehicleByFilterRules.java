package com.sicom.ms.domain.usecase.dashboardgases.totalconvertvehicles;

import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicleFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTotalConvertVehicleByFilterRules {

    public static final Rules<TotalConvertVehicleFilters> GET_CONVERT_VEHICLE_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(TotalConvertVehicleFilters::getGarageSicomCode, "totalConvertVehicleFilters", "garageSicomCode"),
            Rule.of("totalConvertVehicleFilters.garageSicomCodeCanNotBeEmpty",
                    "you must send garage sicom code",
                    object -> (!object.getGarageSicomCode().equals("-1") || object.getGarageSicomCode().equals(null)))
    );
}
