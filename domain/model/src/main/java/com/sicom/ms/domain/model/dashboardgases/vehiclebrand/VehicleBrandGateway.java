package com.sicom.ms.domain.model.dashboardgases.vehiclebrand;

import reactor.core.publisher.Flux;

public interface VehicleBrandGateway {
    Flux<VehicleBrand> getVehicleBrandsByFilters(VehicleBrandFilters request);
}
