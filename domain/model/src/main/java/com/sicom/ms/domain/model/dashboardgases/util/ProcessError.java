package com.sicom.ms.domain.model.dashboardgases.util;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProcessError {
    public String errorCode;
    public String errorMessage;
}
