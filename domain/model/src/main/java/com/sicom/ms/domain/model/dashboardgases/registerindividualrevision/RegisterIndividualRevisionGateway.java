package com.sicom.ms.domain.model.dashboardgases.registerindividualrevision;

import reactor.core.publisher.Mono;

public interface RegisterIndividualRevisionGateway {
    Mono<RegisterIndividualRevisionResponse> createRegisterIndividualRevision(RegisterIndividualRevisionRequest request);
}
