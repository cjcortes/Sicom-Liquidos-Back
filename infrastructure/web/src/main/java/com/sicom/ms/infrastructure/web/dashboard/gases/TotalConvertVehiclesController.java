package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicleFilters;
import com.sicom.ms.domain.usecase.dashboardgases.totalconvertvehicles.GetTotalConvertVehiclesByFiltersUseCase;
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
public class TotalConvertVehiclesController {

    private final AuthenticationGateway authenticationGateway;

    private final GetTotalConvertVehiclesByFiltersUseCase getTotalConvertVehiclesByFiltersUseCase;

    @GetMapping(value = "/converted-vehicles")
    public Flux<TotalConvertVehicle> getTotalConvertVehicles(@RequestParam(defaultValue = "-1") String garageSicomCode,
                                                             @RequestParam(defaultValue = "-1") String conversionType,
                                                             Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> TotalConvertVehicleFilters.builder()
                        .garageSicomCode(garageSicomCode)
                        .conversionType(conversionType)
                        .build())
                .flatMapMany(getTotalConvertVehiclesByFiltersUseCase::getByFilters);
    }
}
