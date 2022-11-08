package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClass;
import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClassFilters;
import com.sicom.ms.domain.usecase.dashboardgases.vehicleclass.GetVehicleClassByFiltersUseCase;
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
public class VehicleClassController {

    private final AuthenticationGateway authenticationGateway;

    private final GetVehicleClassByFiltersUseCase getVehicleClassByFiltersUseCase;

    @GetMapping(value = "/vehicle-class")
    public Flux<VehicleClass> getVehicleClass(@RequestParam(defaultValue = "-1") String vehicleClassId,
                                                 Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> VehicleClassFilters.builder()
                        .vehicleClassId(vehicleClassId)
                        .build())
                .flatMapMany(getVehicleClassByFiltersUseCase::getByFilters);
    }
}
