

package com.sicom.ms.domain.usecase.dashboard;

import com.sicom.ms.domain.model.dashboard.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;


@RequiredArgsConstructor
public class GetConsumptionAndQuotaDashboardUseCase {
    private final DashboardConsumptionQuotaGateway dashboardConsumptionQuotaGateway;

    public Flux<DashboardConsumptionQuota> getConsumptionAndQuota(DashboardConsumptionQuotaFilters dashboardConsumptionQuotaFilters) {
        return dashboardConsumptionQuotaGateway.getConsumptionAndQuota(dashboardConsumptionQuotaFilters);
    }
}
