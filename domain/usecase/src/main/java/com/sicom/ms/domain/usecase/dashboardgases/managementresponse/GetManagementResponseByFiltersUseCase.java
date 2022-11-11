package com.sicom.ms.domain.usecase.dashboardgases.managementresponse;

import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponse;
import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponseFilters;
import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponseGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetManagementResponseByFiltersUseCase {

    private final ManagementResponseGateway managementResponseGateway;

    public Flux<ManagementResponse> getByFilters(ManagementResponseFilters filters){
        return managementResponseGateway.getManagementResponseByFilters(filters);
    }
}
