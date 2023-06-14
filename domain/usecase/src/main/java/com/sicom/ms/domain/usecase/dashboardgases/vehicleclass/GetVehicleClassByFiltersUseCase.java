package com.sicom.ms.domain.usecase.dashboardgases.vehicleclass;

import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClass;
import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClassFilters;
import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClassGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetVehicleClassByFiltersUseCase {

    private final VehicleClassGateway vehicleClassGateway;

    public Flux<VehicleClass> getByFilters(VehicleClassFilters filters){
        return  vehicleClassGateway.getVehicleClassByFilters(filters);
    }
}
