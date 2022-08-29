package com.sicom.ms.domain.usecase.dashboardgases.gcvfuelprices;

import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPrice;
import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPriceFilters;
import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPricesGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetGCVFuelPricesByFiltersUseCase {

    private final GcvFuelPricesGateway gcvFuelPricesGateway;

    public Flux<GcvFuelPrice> getByFilters(GcvFuelPriceFilters filters){
        return  gcvFuelPricesGateway.getGCVFuelPriceByFilters(filters);
    }
}
