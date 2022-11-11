package com.sicom.ms.domain.model.dashboardgases.servicetype;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ServiceTypeFilters {
    String vehicleServiceTypeId;
}
