package com.sicom.ms.domain.model.dashboardgases.participationgarages;

import reactor.core.publisher.Flux;

public interface ParticipationGarageGateway {
    Flux<ParticipationGarage> getParticipationGarageByFilters (ParticipationGarageFilters request);
}
