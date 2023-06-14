package com.sicom.ms.domain.model.dashboardgases.util;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Error {
    String code;
    String message;
    int status;
}
