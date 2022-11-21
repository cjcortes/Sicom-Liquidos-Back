package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionRequest;
import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionResponse;
import com.sicom.ms.domain.usecase.dashboardgases.registerindividualrevision.CreateRegisterIndividualRevisionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class RegisterIndividualRevisionController {

    private final CreateRegisterIndividualRevisionUseCase createRegisterIndividualRevisionUseCase;

    @PostMapping(value = "/register-individual-revision")
    public Mono<RegisterIndividualRevisionResponse> registerIndividualRevision(@RequestBody RegisterIndividualRevisionRequest request) {
        return createRegisterIndividualRevisionUseCase.create(request);
    }
}
