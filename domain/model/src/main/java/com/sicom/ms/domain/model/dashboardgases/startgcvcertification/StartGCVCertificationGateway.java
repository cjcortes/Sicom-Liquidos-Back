package com.sicom.ms.domain.model.dashboardgases.startgcvcertification;

import reactor.core.publisher.Mono;

public interface StartGCVCertificationGateway {
    Mono<StartGCVCertificationResponse> createStarGCVCertification(StartGCVCertificationRequest request);
}
