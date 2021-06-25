package com.sicom.ms.domain.usecase.dashboard;

import com.sicom.ms.domain.model.dashboard.DashboardTotalsFilters;
import com.sicom.ms.domain.model.dashboard.DashboardTotalsGateway;
import com.sicom.ms.domain.model.dashboard.DashboardTotal;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
public class GetTotalsDashboardUseCase {
    private final DashboardTotalsGateway dashboardTotalsGateway;

    public Flux<DashboardTotal> getTotals(DashboardTotalsFilters dashboardTotalsFilters) {
        return dashboardTotalsGateway.getTotals(dashboardTotalsFilters);
    }
}
