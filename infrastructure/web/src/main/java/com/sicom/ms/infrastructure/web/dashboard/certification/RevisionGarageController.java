package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarage;
import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarageFilters;
import com.sicom.ms.domain.usecase.dashboardgases.revisiongarage.GetRevisionGarageByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class RevisionGarageController {

    private final AuthenticationGateway authenticationGateway;

    private final GetRevisionGarageByFiltersUseCase getRevisionGarageByFiltersUseCase;

    @GetMapping(value = "/revision-garage")
    public Flux<RevisionGarage> getRevisionGarage(@RequestParam(defaultValue = "-1") String revisionGarageId,
                                                    Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> RevisionGarageFilters.builder()
                        .revisionGarageId(revisionGarageId)
                        .build())
                .flatMapMany(getRevisionGarageByFiltersUseCase::getByFilters);
    }
}
