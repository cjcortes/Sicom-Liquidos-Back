package com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation;

import reactor.core.publisher.Flux;

public interface IndividualRevisionInformationGateway {
    Flux<IndividualRevisionInformationResponse> getIndividualRevisionInformation (IndividualRevisionInformationFilters filters);
}
