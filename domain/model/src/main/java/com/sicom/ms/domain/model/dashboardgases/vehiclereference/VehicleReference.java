package com.sicom.ms.domain.model.dashboardgases.vehiclereference;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleReference {
    int vehicleReferenceId;
    int vehicleBrandId;
    String vehicleBrandName;
    int vehicleClassId;
    String vehicleClassName;
    String reference1;
    String reference2;
    String reference3;
    String vehicleReferenceCode;
    int vehicleFuelTypeId;
    String fuelTypeName;
    int vehicleServiceTypeId;
    String vehicleServiceTypeName;
}
