package com.sicom.ms.domain.model.dashboardgases.totalcertifiers;


import reactor.core.publisher.Flux;

public interface TotalCertifierGateway {
    Flux<TotalCertifier> getTotalCertifiersByFilters (TotalCertifierFilters request);
}
