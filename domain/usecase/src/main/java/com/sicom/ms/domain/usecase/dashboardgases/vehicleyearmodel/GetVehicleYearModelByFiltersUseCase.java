package com.sicom.ms.domain.usecase.dashboardgases.vehicleyearmodel;

import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModel;
import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModelFilters;
import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModelGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetVehicleYearModelByFiltersUseCase {
    private final VehicleYearModelGateway vehicleYearModelGateway;

    public Flux<VehicleYearModel> getByFilters(VehicleYearModelFilters filters){
        return vehicleYearModelGateway.getVehicleYearModelByFilters(filters);
    }
}
