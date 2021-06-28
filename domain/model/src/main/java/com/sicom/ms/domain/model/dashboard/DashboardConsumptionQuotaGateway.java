
package com.sicom.ms.domain.model.dashboard;

import reactor.core.publisher.Flux;

public interface DashboardConsumptionQuotaGateway {

    Flux<DashboardConsumptionQuota> getConsumptionAndQuota(DashboardConsumptionQuotaFilters dashboardConsumptionQuotaFilters);

}
