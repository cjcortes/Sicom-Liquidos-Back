package com.sicom.ms.domain.model.dashboard;

import reactor.core.publisher.Flux;

public interface DashboardGateway {
    Flux<DashboardTotal> getTotals(DashboardFilters dashboardFilters);

}
