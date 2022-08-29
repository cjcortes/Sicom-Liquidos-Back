package com.sicom.ms.domain.usecase.dashboardgases.departmentcities;

import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCity;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCityFilters;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentsCitiesGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetDepartmentCityByFiltersUseCase {

    private final DepartmentsCitiesGateway departmentsCitiesGateway;

    public Flux<DepartmentCity> getByFilters(DepartmentCityFilters filters){
        return departmentsCitiesGateway.getDepartmentCity(filters);
    }
}
