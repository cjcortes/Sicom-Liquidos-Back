package com.sicom.ms.domain.model.dashboardgases.vehiclereference;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleReferenceFilters {
    String vehicleReferenceId;
    String vehicleBrandId;
    String vehicleClassId;
    String reference1;
    String reference2;
    String reference3;
}
