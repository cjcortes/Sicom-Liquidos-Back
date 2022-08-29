package com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TotalConvertVehicleFilters {

    String garageSicomCode;
    String conversionType;
}
