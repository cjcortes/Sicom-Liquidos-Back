package com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TotalConvertVehicle {

    String name;
    String value;
    int numericValue;
}
