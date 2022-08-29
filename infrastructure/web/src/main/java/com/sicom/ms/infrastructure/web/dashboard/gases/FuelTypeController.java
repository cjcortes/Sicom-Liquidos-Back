package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCity;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCityFilters;
import com.sicom.ms.domain.model.dashboardgases.fueltypes.FuelType;
import com.sicom.ms.domain.usecase.dashboardgases.departmentcities.GetDepartmentCityByFiltersUseCase;
import com.sicom.ms.domain.usecase.dashboardgases.fueltypes.GetFuelTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/fuel-type")
@RequiredArgsConstructor
public class FuelTypeController {

    private final AuthenticationGateway authenticationGateway;

    private final GetFuelTypeUseCase getFuelTypeUseCase;

    @GetMapping
    public Flux<FuelType> getFuelType(@RequestParam(defaultValue = "-1") String fuelCode,
                                        Principal principal) {

        return getFuelTypeUseCase.getByFuelCode(fuelCode);
    }
}
