package com.sicom.ms.domain.usecase.dashboardgases.startgcvcertification;

import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationGateway;
import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationRequest;
import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateGCVCertificationUseCase {

    private final StartGCVCertificationGateway startGCVCertificationGateway;

    public Mono<StartGCVCertificationResponse> create(StartGCVCertificationRequest request){
        return startGCVCertificationGateway.createStarGCVCertification(request);
    }
}
