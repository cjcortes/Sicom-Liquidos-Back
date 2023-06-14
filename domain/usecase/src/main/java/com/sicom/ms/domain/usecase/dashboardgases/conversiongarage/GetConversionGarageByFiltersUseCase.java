package com.sicom.ms.domain.usecase.dashboardgases.conversiongarage;

import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarage;
import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarageFilters;
import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarageGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetConversionGarageByFiltersUseCase {

    private final ConversionGarageGateway conversionGarageGateway;

    public Flux<ConversionGarage> getByFilters(ConversionGarageFilters filters){
        return conversionGarageGateway.getConversionGarageByFilters(filters);
    }
}
