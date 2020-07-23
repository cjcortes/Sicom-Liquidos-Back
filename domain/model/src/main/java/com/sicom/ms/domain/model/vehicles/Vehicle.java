package com.sicom.ms.domain.model.vehicles;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Vehicle {

    String transportType;
    String headPlate;
    String trailer;
    int capacity;
    String driver;
    String driverIdentification;
    int compartment;

}
