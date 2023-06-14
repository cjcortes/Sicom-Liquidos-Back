package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationFilters;
import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationResponse;
import com.sicom.ms.domain.usecase.dashboardgases.individualrevisioninformation.GetIndividualRevisionInformationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class IndividualRevisionInformationController {

    private final GetIndividualRevisionInformationUseCase getIndividualRevisionInformationUseCase;

    @GetMapping(value = "/individual-revision-information")
    public Flux<IndividualRevisionInformationResponse> getIndividualRevisionInformation(@RequestParam(defaultValue = "-1") String placa,
                                                                                        @RequestParam(defaultValue = "-1") String chip,
                                                                                        @RequestParam(defaultValue = "-1") String VIN,
                                                                                        @RequestParam(defaultValue = "-1") String numeroCaso) {

        return getIndividualRevisionInformationUseCase.getByFilters(IndividualRevisionInformationFilters.builder()
                .chip(chip)
                .vin(VIN)
                .placa(placa)
                .numeroCaso(numeroCaso).build());
    }
}
