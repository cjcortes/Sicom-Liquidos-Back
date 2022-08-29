package com.sicom.ms.domain.model.dashboardgases.gcvfuelprices;

import reactor.core.publisher.Flux;

public interface GcvFuelPricesGateway {
    Flux<GcvFuelPrice> getGCVFuelPriceByFilters (GcvFuelPriceFilters request);
}
