package com.sicom.ms.domain.usecase.dashboardgases.lastcertificationstatus;

import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatus;
import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatusFilters;
import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatusGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetLastCertificationStatusByFiltersUseCase {

    private final LastCertificationStatusGateway lastCertificationStatusGateway;

    public Flux<LastCertificationStatus> getByFilters(LastCertificationStatusFilters filters){
        return lastCertificationStatusGateway.getLastCertificationStatusByFilters(filters);
    }
}
