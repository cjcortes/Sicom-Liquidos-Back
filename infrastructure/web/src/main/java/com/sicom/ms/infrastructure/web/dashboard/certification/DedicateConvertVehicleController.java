package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicleFilters;
import com.sicom.ms.domain.usecase.dashboardgases.dedicateconvertvehicle.GetDedicateConvertVehicleByFiltersUseCase;
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
public class DedicateConvertVehicleController {

    private final AuthenticationGateway authenticationGateway;

    private final GetDedicateConvertVehicleByFiltersUseCase getDedicateConvertVehicleByFiltersUseCase;

    @GetMapping(value = "/dedicated-converted")
    public Flux<DedicateConvertVehicle> getDedicatedConvertedVehicle(@RequestParam(defaultValue = "-1") String dedicateConvertId,
                                                        Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> DedicateConvertVehicleFilters.builder()
                        .dedicateConvertId(dedicateConvertId)
                        .build())
                .flatMapMany(getDedicateConvertVehicleByFiltersUseCase::getByFilters);
    }
}
