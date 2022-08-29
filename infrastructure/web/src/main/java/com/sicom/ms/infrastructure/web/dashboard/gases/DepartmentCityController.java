package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCity;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCityFilters;
import com.sicom.ms.domain.usecase.dashboardgases.departmentcities.GetDepartmentCityByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/visitor/department-city")
@RequiredArgsConstructor
public class DepartmentCityController {

    private final AuthenticationGateway authenticationGateway;

    private final GetDepartmentCityByFiltersUseCase getDepartmentCityByFiltersUseCase;

    @GetMapping
    public Flux<DepartmentCity> getDepartmentCity(@RequestParam(defaultValue = "-1") String departmentCode,
                                              @RequestParam(defaultValue = "-1") String cityCode,
                                              Principal principal) {

        return getDepartmentCityByFiltersUseCase.getByFilters(DepartmentCityFilters.builder()
                .departmentCode(departmentCode)
                .cityCode(cityCode)
                .build());
    }
}
