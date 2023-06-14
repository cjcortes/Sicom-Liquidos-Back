package com.sicom.ms.domain.usecase.dashboardgases.totalconvertvehicles;

import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehiclesGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.totalconvertvehicles.GetTotalConvertVehicleByFilterRules.GET_CONVERT_VEHICLE_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetTotalConvertVehiclesByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final TotalConvertVehiclesGateway totalConvertVehiclesGateway;

    public Flux<TotalConvertVehicle> getByFilters(TotalConvertVehicleFilters totalConvertVehicleFilters){
        objectValidator.validate(totalConvertVehicleFilters, GET_CONVERT_VEHICLE_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getTotalConvertVehicles");
        return totalConvertVehiclesGateway.getTotalConvertVehiclesByFilters(totalConvertVehicleFilters);
    }
}
