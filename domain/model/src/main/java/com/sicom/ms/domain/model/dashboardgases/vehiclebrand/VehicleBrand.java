package com.sicom.ms.domain.model.dashboardgases.vehiclebrand;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleBrand {
    int vehicleBrandId;
    String vehicleBrand;
    int vehicleBrandCode;
}
