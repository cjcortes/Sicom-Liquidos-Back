package com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype;

import reactor.core.publisher.Flux;

public interface VehicleRevisionTypeGateway {
    Flux<VehicleRevisionTypeResponse> getVehicleRevisionType(VehicleRevisionTypeFilters filters);
}
