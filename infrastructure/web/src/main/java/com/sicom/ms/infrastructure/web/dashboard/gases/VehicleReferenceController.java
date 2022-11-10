package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReference;
import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReferenceFilters;
import com.sicom.ms.domain.usecase.dashboardgases.vehiclereference.GetVehicleReferenceByFiltersUseCase;
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
public class VehicleReferenceController {

    private final AuthenticationGateway authenticationGateway;

    private final GetVehicleReferenceByFiltersUseCase getVehicleReferenceByFiltersUseCase;

    @GetMapping(value = "/vehicle-reference")
    public Flux<VehicleReference> getVehicleReference(@RequestParam(defaultValue = "-1") String vehicleReferenceId,
                                                      @RequestParam(defaultValue = "-1") String vehicleBrandId,
                                                      @RequestParam(defaultValue = "-1") String vehicleClassId,
                                                      @RequestParam(defaultValue = "-1") String reference1,
                                                      @RequestParam(defaultValue = "-1") String reference2,
                                                      @RequestParam(defaultValue = "-1") String reference3,
                                                      Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> VehicleReferenceFilters.builder()
                        .vehicleReferenceId(vehicleReferenceId)
                        .vehicleBrandId(vehicleBrandId)
                        .vehicleClassId(vehicleClassId)
                        .reference1(reference1)
                        .reference2(reference2)
                        .reference3(reference3)
                        .build())
                .flatMapMany(getVehicleReferenceByFiltersUseCase::getByFilters);
    }
}
