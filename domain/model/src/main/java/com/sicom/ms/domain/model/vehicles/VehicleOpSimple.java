package com.sicom.ms.domain.model.vehicles;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleOpSimple {
    public String idMAssociation;
    public String identification;
    public String vehicleType;
    public String transportName;
    public String totalCapacity;
    public String compartments;
    public String driver;
    public String status;

}
