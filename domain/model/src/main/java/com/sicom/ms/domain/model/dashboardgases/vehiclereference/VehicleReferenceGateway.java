package com.sicom.ms.domain.model.dashboardgases.vehiclereference;

import reactor.core.publisher.Flux;

public interface VehicleReferenceGateway {
    Flux<VehicleReference> getVehicleReferenceByFilters(VehicleReferenceFilters request);
}
