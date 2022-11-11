package com.sicom.ms.domain.model.dashboardgases.revisiongarage;

import reactor.core.publisher.Flux;

public interface RevisionGarageGateway {
    Flux<RevisionGarage> getRevisionGarageByFilters(RevisionGarageFilters request);
}
