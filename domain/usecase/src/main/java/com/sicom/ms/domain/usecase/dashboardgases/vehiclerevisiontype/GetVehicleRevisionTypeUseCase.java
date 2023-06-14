package com.sicom.ms.domain.usecase.dashboardgases.vehiclerevisiontype;

import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeGateway;
import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetVehicleRevisionTypeUseCase {

    private final VehicleRevisionTypeGateway vehicleRevisionTypeGateway;

    public Flux<VehicleRevisionTypeResponse> getByFilters(VehicleRevisionTypeFilters filters){
        return  vehicleRevisionTypeGateway.getVehicleRevisionType(filters);
    }
}
