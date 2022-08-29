package com.sicom.ms.domain.usecase.dashboardgases.totalconvertvehicles;

import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehiclesGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetTotalConvertVehiclesByFiltersUseCase {

    private final TotalConvertVehiclesGateway totalConvertVehiclesGateway;

    public Flux<TotalConvertVehicle> getByFilters(TotalConvertVehicleFilters totalConvertVehicleFilters){
        return totalConvertVehiclesGateway.getTotalConvertVehiclesByFilters(totalConvertVehicleFilters);
    }
}
