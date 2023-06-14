package com.sicom.ms.domain.model.dashboardgases.vehicleclass;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleClass {

    int vehicleClassId;
    String vehicleClass;
    String vehicleClassCode;
}
