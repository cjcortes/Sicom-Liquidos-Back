package com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle;

import reactor.core.publisher.Flux;

public interface DedicateConvertVehicleGateway {
    Flux<DedicateConvertVehicle> getDedicateConvertVehiclesByFilters(DedicateConvertVehicleFilters request);
}
