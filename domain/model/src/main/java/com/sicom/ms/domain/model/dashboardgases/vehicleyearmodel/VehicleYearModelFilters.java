package com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleYearModelFilters {
    String vehicleYearModelId;
}
