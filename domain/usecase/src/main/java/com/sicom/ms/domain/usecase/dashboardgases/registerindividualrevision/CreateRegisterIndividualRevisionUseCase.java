package com.sicom.ms.domain.usecase.dashboardgases.registerindividualrevision;

import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionGateway;
import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionRequest;
import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateRegisterIndividualRevisionUseCase {

    private final RegisterIndividualRevisionGateway registerIndividualRevisionGateway;

    public Mono<RegisterIndividualRevisionResponse> create(RegisterIndividualRevisionRequest request){
        return registerIndividualRevisionGateway.createRegisterIndividualRevision(request);
    }
}
