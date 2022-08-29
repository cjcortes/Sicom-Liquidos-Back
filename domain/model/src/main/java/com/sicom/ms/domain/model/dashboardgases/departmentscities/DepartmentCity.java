package com.sicom.ms.domain.model.dashboardgases.departmentscities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DepartmentCity {

    String cityCode;
    String cityName;
    String departmentCode;
    String departmentName;
}
