package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationRequest;
import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationResponse;
import com.sicom.ms.domain.usecase.dashboardgases.startgcvcertification.CreateGCVCertificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class StartGCVCertificationController {

    private final CreateGCVCertificationUseCase createGCVCertificationUseCase;

    @PostMapping(value = "/start-gcv-certification")
    public Mono<StartGCVCertificationResponse> startGCVCertification(@RequestBody StartGCVCertificationRequest request) {
        return createGCVCertificationUseCase.create(request);
    }
}
