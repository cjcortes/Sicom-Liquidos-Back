package com.sicom.ms.domain.usecase.dashboardgases.revisiongarage;

import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarage;
import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarageFilters;
import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarageGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetRevisionGarageByFiltersUseCase {

    private final RevisionGarageGateway revisionGarageGateway;

    public Flux<RevisionGarage> getByFilters(RevisionGarageFilters filters){
        return revisionGarageGateway.getRevisionGarageByFilters(filters);
    }
}
