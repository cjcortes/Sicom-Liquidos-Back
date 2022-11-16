package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModel;
import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModelFilters;
import com.sicom.ms.domain.usecase.dashboardgases.vehicleyearmodel.GetVehicleYearModelByFiltersUseCase;
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
public class VehicleYearModelController {

    private final AuthenticationGateway authenticationGateway;

    private final GetVehicleYearModelByFiltersUseCase getVehicleYearModelByFiltersUseCase;

    @GetMapping(value = "/vehicle-year-model")
    public Flux<VehicleYearModel> getVehicleYearModel(@RequestParam(defaultValue = "-1") String vehicleYearModelId,
                                                      Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> VehicleYearModelFilters.builder()
                        .vehicleYearModelId(vehicleYearModelId)
                        .build())
                .flatMapMany(getVehicleYearModelByFiltersUseCase::getByFilters);
    }
}
