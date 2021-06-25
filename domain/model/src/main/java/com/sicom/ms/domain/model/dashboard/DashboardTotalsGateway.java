package com.sicom.ms.domain.model.dashboard;

import reactor.core.publisher.Flux;

public interface DashboardTotalsGateway {
    Flux<DashboardTotal> getTotals(DashboardTotalsFilters dashboardTotalsFilters);

}
