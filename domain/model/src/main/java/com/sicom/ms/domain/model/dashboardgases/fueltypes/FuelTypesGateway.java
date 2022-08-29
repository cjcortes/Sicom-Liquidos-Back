package com.sicom.ms.domain.model.dashboardgases.fueltypes;

import reactor.core.publisher.Flux;

public interface FuelTypesGateway {
    Flux<FuelType> getByFuelType(String fuelCode);
}
