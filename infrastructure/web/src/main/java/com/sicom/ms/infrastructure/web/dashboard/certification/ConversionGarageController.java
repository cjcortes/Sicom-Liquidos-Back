package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarage;
import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarageFilters;
import com.sicom.ms.domain.usecase.dashboardgases.conversiongarage.GetConversionGarageByFiltersUseCase;
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
public class ConversionGarageController {

    private final AuthenticationGateway authenticationGateway;

    private final GetConversionGarageByFiltersUseCase getConversionGarageByFiltersUseCase;

    @GetMapping(value = "/conversion-garage")
    public Flux<ConversionGarage> getConversionGarage(@RequestParam(defaultValue = "-1") String conversionGarageId,
                                                        Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> ConversionGarageFilters.builder()
                        .conversionGarageId(conversionGarageId)
                        .build())
                .flatMapMany(getConversionGarageByFiltersUseCase::getByFilters);
    }
}
