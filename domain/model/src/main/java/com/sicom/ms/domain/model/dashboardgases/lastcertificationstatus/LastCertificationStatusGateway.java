package com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus;

import reactor.core.publisher.Flux;

public interface LastCertificationStatusGateway {
    Flux<LastCertificationStatus> getLastCertificationStatusByFilters(LastCertificationStatusFilters request);
}
