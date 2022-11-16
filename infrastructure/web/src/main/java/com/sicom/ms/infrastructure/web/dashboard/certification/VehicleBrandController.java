package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.vehiclebrand.VehicleBrand;
import com.sicom.ms.domain.model.dashboardgases.vehiclebrand.VehicleBrandFilters;
import com.sicom.ms.domain.usecase.dashboardgases.vehiclebrand.GetVehicleBrandByFiltersUseCase;
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
public class VehicleBrandController {

    private final AuthenticationGateway authenticationGateway;

    private final GetVehicleBrandByFiltersUseCase getVehicleBrandByFiltersUseCase;

    @GetMapping(value = "/vehicle-brand")
    public Flux<VehicleBrand> getVehicleBrand(@RequestParam(defaultValue = "-1") String vehicleBrandId,
                                                         Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> VehicleBrandFilters.builder()
                        .vehicleBrandId(vehicleBrandId)
                        .build())
                .flatMapMany(getVehicleBrandByFiltersUseCase::getByFilters);
    }
}
