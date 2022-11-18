package com.sicom.ms.domain.usecase.dashboardgases.individualrevisioninformation;

import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationFilters;
import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationGateway;
import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetIndividualRevisionInformationUseCase {

    private final IndividualRevisionInformationGateway individualRevisionInformationGateway;

    public Flux<IndividualRevisionInformationResponse> getByFilters(IndividualRevisionInformationFilters filters){
        return individualRevisionInformationGateway.getIndividualRevisionInformation(filters);
    }
}
