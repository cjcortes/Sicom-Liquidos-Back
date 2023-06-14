package com.sicom.ms.domain.model.dashboardgases.departmentscities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DepartmentCityFilters {

    String cityCode;
    String departmentCode;
}
