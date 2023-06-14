package com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel;

import reactor.core.publisher.Flux;

public interface VehicleYearModelGateway {
    Flux<VehicleYearModel> getVehicleYearModelByFilters (VehicleYearModelFilters request);
}
