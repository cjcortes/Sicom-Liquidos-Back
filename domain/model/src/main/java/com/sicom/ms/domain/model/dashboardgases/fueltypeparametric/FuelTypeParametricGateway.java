package com.sicom.ms.domain.model.dashboardgases.fueltypeparametric;

import reactor.core.publisher.Flux;

public interface FuelTypeParametricGateway {
    Flux<FuelTypeParametric> getFuelTypeParametricByFilters(FuelTypeParametricFilters request);
}
