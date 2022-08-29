package com.sicom.ms.domain.usecase.dashboardgases.totalsites;

import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSite;
import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSiteFilters;
import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSitesGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetTotalSitesByFiltersUseCase {

    private final TotalSitesGateway totalSitesGateway;

    public Flux<TotalSite> getByFilters(TotalSiteFilters filters){
        return  totalSitesGateway.getTotalSitesByFilters(filters);
    }
}
