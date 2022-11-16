package com.sicom.ms.domain.model.dashboardgases.garagecoordinates;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Error {
    String code;
    String message;
    int status;
}
