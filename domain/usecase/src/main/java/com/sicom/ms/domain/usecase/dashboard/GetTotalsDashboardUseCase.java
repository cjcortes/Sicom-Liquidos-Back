package com.sicom.ms.domain.usecase.dashboard;

import com.sicom.ms.domain.model.dashboard.DashboardFilters;
import com.sicom.ms.domain.model.dashboard.DashboardGateway;
import com.sicom.ms.domain.model.dashboard.DashboardTotal;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
public class GetTotalsDashboardUseCase {
    private final DashboardGateway dashboardGateway;

    public Flux<DashboardTotal> getTotals(DashboardFilters dashboardFilters) {
        return dashboardGateway.getTotals(dashboardFilters);
    }
}
