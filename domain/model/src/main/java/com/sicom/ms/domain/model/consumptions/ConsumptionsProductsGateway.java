package com.sicom.ms.domain.model.consumptions;

import reactor.core.publisher.Flux;

public interface ConsumptionsProductsGateway {

    Flux<ConsumptionProduct> getConsumptionsProducts(ConsumptionsProductsFilters consumptionsProductsFilters);

}
