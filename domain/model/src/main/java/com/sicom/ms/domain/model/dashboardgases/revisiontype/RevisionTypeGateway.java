package com.sicom.ms.domain.model.dashboardgases.revisiontype;

import reactor.core.publisher.Flux;

public interface RevisionTypeGateway {
    Flux<RevisionType> getRevisionTypeByFilters(RevisionTypeFilters request);
}
