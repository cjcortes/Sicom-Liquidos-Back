package com.sicom.ms.domain.model.dashboardgases.servicetype;

import reactor.core.publisher.Flux;

public interface ServiceTypeGateway {
    Flux<ServiceType> getServiceTypeByFilters(ServiceTypeFilters request);
}
