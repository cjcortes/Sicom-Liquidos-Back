package com.sicom.ms.domain.model.dashboardgases.gcvfuelprices;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class GcvFuelPrice {

    String name;
    String value;
    int numericValue;
}
