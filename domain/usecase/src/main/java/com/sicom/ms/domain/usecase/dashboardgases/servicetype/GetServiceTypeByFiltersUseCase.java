package com.sicom.ms.domain.usecase.dashboardgases.servicetype;

import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceType;
import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceTypeGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetServiceTypeByFiltersUseCase {

    private final ServiceTypeGateway serviceTypeGateway;

    public Flux<ServiceType> getByFilters(ServiceTypeFilters filters){
        return serviceTypeGateway.getServiceTypeByFilters(filters);
    }
}
