package com.sicom.ms.domain.model.dashboardgases.vehicleclass;

import reactor.core.publisher.Flux;

public interface VehicleClassGateway {
    Flux<VehicleClass> getVehicleClassByFilters(VehicleClassFilters request);
}
