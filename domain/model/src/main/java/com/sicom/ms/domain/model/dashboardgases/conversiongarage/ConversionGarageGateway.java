package com.sicom.ms.domain.model.dashboardgases.conversiongarage;

import reactor.core.publisher.Flux;

public interface ConversionGarageGateway {
    Flux<ConversionGarage> getConversionGarageByFilters(ConversionGarageFilters request);
}
