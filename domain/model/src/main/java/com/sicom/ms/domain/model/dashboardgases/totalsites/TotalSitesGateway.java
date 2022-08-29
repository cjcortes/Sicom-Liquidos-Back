package com.sicom.ms.domain.model.dashboardgases.totalsites;

import reactor.core.publisher.Flux;

public interface TotalSitesGateway {
    Flux<TotalSite> getTotalSitesByFilters (TotalSiteFilters request);
}
