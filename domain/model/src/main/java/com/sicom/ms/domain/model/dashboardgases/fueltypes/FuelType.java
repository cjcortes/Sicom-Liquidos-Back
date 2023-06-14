package com.sicom.ms.domain.model.dashboardgases.fueltypes;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class FuelType {

    String fuelName;
    String fuelCode;
}
