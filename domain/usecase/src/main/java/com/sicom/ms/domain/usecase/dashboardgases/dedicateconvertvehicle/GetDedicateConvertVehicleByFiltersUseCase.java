package com.sicom.ms.domain.usecase.dashboardgases.dedicateconvertvehicle;

import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetDedicateConvertVehicleByFiltersUseCase {

    private final DedicateConvertVehicleGateway dedicateConvertVehicleGateway;

    public Flux<DedicateConvertVehicle> getByFilters(DedicateConvertVehicleFilters filters){
        return dedicateConvertVehicleGateway.getDedicateConvertVehiclesByFilters(filters);
    }
}
