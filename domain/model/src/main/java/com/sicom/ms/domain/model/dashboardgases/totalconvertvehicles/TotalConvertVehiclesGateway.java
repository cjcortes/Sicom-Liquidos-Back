package com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles;

import reactor.core.publisher.Flux;

public interface TotalConvertVehiclesGateway {
    Flux<TotalConvertVehicle> getTotalConvertVehiclesByFilters (TotalConvertVehicleFilters request);
}
