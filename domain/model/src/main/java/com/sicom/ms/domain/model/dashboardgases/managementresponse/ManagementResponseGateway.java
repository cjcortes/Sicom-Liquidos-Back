package com.sicom.ms.domain.model.dashboardgases.managementresponse;

import reactor.core.publisher.Flux;

public interface ManagementResponseGateway {
    Flux<ManagementResponse> getManagementResponseByFilters(ManagementResponseFilters request);
}
