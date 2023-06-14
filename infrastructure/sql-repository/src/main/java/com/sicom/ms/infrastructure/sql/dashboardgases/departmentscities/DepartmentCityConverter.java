package com.sicom.ms.infrastructure.sql.dashboardgases.departmentscities;

import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCity;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentCityConverter extends ObjectConverter<DepartmentCity, DepartmentCityData> {
}
