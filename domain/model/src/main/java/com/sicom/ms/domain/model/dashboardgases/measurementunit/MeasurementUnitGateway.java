package com.sicom.ms.domain.model.dashboardgases.measurementunit;

import reactor.core.publisher.Flux;

public interface MeasurementUnitGateway {
    Flux<MeasurementUnit> getMeasurementUnitByFilters(MeasurementUnitFilters request);
}
