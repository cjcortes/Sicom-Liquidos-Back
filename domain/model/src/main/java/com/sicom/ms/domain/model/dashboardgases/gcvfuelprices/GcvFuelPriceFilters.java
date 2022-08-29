package com.sicom.ms.domain.model.dashboardgases.gcvfuelprices;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GcvFuelPriceFilters {

    String departmentCode;
    String cityCode;
    String fuelType;
}
