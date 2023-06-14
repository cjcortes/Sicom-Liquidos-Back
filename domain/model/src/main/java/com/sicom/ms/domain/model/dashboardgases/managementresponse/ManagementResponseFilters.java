package com.sicom.ms.domain.model.dashboardgases.managementresponse;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ManagementResponseFilters {
    String managementResponseId;
}
