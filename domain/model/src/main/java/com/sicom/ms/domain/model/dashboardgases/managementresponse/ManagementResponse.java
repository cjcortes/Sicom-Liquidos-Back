package com.sicom.ms.domain.model.dashboardgases.managementresponse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ManagementResponse {
    int managementResponseId;
    String managementResponseName;
    String managementResponseCode;
}
