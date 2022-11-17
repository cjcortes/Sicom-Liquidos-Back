package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.Error;
import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesResponse;
import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPrice;
import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPriceFilters;
import com.sicom.ms.domain.usecase.dashboardgases.gcvfuelprices.GetGCVFuelPricesByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/visitor/fuel-price")
@RequiredArgsConstructor
public class GcvFuelPriceController {

    private final AuthenticationGateway authenticationGateway;

    private final GetGCVFuelPricesByFiltersUseCase getGCVFuelPricesByFiltersUseCase;

    @GetMapping(value = "/gcv")
    public Flux<GcvFuelPrice> getGCVFuelPrice(@RequestParam(defaultValue = "-1") String departmentCode,
                                              @RequestParam(defaultValue = "-1") String cityCode,
                                              @RequestParam(defaultValue = "-1") String fuelType,
                                              Principal principal) {

        return getGCVFuelPricesByFiltersUseCase.getByFilters(GcvFuelPriceFilters.builder()
                .departmentCode(departmentCode)
                .cityCode(cityCode)
                .fuelType(fuelType)
                .build());
    }
}
