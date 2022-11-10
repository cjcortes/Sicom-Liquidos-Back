package com.sicom.ms.domain.usecase.dashboardgases.vehiclereference;

import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReferenceFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetVehicleReferenceByFiltersRules {

    public static final Rules<VehicleReferenceFilters> GET_VEHICLE_REFERENCE_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(VehicleReferenceFilters::getVehicleBrandId, "vehicleReferenceFilters", "vehicleBrandId"),
            Rule.of("vehicleReferenceFilters.vehicleBrandIdCanNotBeEmpty",
                    "you must send equipment brand id",
                    object -> (!object.getVehicleReferenceId().equals("-1") || object.getVehicleBrandId().equals(null))),
            cannotBeEmpty(VehicleReferenceFilters::getVehicleClassId, "vehicleReferenceFilters", "vehicleClassId"),
            Rule.of("vehicleReferenceFilters.vehicleClassIdCanNotBeEmpty",
                    "you must send equipment brand id",
                    object -> (!object.getVehicleReferenceId().equals("-1") || object.getVehicleClassId().equals(null)))
    );
}
