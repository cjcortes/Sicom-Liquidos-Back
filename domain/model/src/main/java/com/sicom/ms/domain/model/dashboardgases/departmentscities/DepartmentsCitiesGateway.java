package com.sicom.ms.domain.model.dashboardgases.departmentscities;

import reactor.core.publisher.Flux;

public interface DepartmentsCitiesGateway {
    Flux<DepartmentCity> getDepartmentCity(DepartmentCityFilters request);
}
