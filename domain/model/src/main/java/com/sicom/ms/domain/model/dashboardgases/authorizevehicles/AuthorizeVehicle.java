package com.sicom.ms.domain.model.dashboardgases.authorizevehicles;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AuthorizeVehicle {

    String name;
    String value;
    int numericValue;
}
