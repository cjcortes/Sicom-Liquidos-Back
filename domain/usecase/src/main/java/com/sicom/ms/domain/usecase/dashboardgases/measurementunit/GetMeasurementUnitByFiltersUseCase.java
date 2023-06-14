package com.sicom.ms.domain.usecase.dashboardgases.measurementunit;

import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnit;
import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnitFilters;
import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnitGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetMeasurementUnitByFiltersUseCase {

    private final MeasurementUnitGateway measurementUnitGateway;

    public Flux<MeasurementUnit> getByFilters(MeasurementUnitFilters filters){
        return measurementUnitGateway.getMeasurementUnitByFilters(filters);
    }
}
