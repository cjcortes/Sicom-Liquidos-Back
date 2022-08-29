package com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions;

import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicle;
import reactor.core.publisher.Flux;

public interface TotalGarageRevisionsGateway {
    Flux<TotalGarageRevision> getTotalGarageRevisionsByFilters (TotalGarageRevisionFilters request);
}
