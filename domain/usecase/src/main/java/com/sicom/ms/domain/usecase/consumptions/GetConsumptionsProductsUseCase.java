package com.sicom.ms.domain.usecase.consumptions;

import com.sicom.ms.domain.model.consumptions.ConsumptionProduct;
import com.sicom.ms.domain.model.consumptions.ConsumptionsProductsFilters;
import com.sicom.ms.domain.model.consumptions.ConsumptionsProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetConsumptionsProductsUseCase {
    private final ConsumptionsProductsGateway consumptionsProductsGateway;

    public Flux<ConsumptionProduct> getConsumptionsProducts(ConsumptionsProductsFilters consumptionsProductsFilters) {
        return consumptionsProductsGateway.getConsumptionsProducts(consumptionsProductsFilters);
    }

}
