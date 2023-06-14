package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarage;
import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarageFilters;
import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifier;
import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifierFilters;
import com.sicom.ms.domain.usecase.dashboardgases.participationgarages.GetParticipationGaragesByFiltersUseCase;
import com.sicom.ms.domain.usecase.dashboardgases.totalcertifiers.GetTotalCertifiersByFiltersUseCase;
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
public class ParticipationGarageController {

    private final AuthenticationGateway authenticationGateway;

    private final GetParticipationGaragesByFiltersUseCase getParticipationGaragesByFiltersUseCase;

    @GetMapping(value = "/participation-garage")
    public Flux<ParticipationGarage> getParticipationGarages(@RequestParam(defaultValue = "-1") String certifierSicomCode,
                                                        Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> ParticipationGarageFilters.builder()
                        .certifierSicomCode(certifierSicomCode)
                        .build())
                .flatMapMany(getParticipationGaragesByFiltersUseCase::getByFilters);
    }
}
