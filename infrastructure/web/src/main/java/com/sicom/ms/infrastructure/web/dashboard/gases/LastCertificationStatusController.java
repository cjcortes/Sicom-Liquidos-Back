package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatus;
import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatusFilters;
import com.sicom.ms.domain.usecase.dashboardgases.lastcertificationstatus.GetLastCertificationStatusByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/certifier")
@RequiredArgsConstructor
public class LastCertificationStatusController {

    private final AuthenticationGateway authenticationGateway;

    private final GetLastCertificationStatusByFiltersUseCase getLastCertificationStatusByFiltersUseCase;

    @GetMapping(value = "/certification-status")
    public Flux<LastCertificationStatus> getLastCertificationStatus(@RequestParam(defaultValue = "-1") String lastCertificationStatusId,
                                                                    Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> LastCertificationStatusFilters.builder()
                        .lastCertificationStatusId(lastCertificationStatusId)
                        .build())
                .flatMapMany(getLastCertificationStatusByFiltersUseCase::getByFilters);
    }
}
