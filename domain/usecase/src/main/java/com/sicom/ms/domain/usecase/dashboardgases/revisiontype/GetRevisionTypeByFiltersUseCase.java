package com.sicom.ms.domain.usecase.dashboardgases.revisiontype;

import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionType;
import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionTypeGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetRevisionTypeByFiltersUseCase {

    private final RevisionTypeGateway revisionTypeGateway;

    public Flux<RevisionType> getByFilters(RevisionTypeFilters filters){
        return revisionTypeGateway.getRevisionTypeByFilters(filters);
    }
}
