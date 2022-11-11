package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametric;
import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametricFilters;
import com.sicom.ms.domain.usecase.dashboardgases.fueltypeparametric.GetFuelTypeParametricByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/garage")
@RequiredArgsConstructor
public class FuelTypeParametricController {

    private final AuthenticationGateway authenticationGateway;

    private final GetFuelTypeParametricByFiltersUseCase getFuelTypeParametricByFiltersUseCase;

    @GetMapping(value = "/fuel-type")
    public Flux<FuelTypeParametric> getFuelType(@RequestParam(defaultValue = "-1") String fuelTypeId,
                                                Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> FuelTypeParametricFilters.builder()
                        .fuelTypeId(fuelTypeId)
                        .build())
                .flatMapMany(getFuelTypeParametricByFiltersUseCase::getByFilters);
    }
}
