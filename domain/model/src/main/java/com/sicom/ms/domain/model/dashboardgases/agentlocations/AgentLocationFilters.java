package com.sicom.ms.domain.model.dashboardgases.agentlocations;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AgentLocationFilters {
    String departmentCode;
    String cityCode;
    String gcvType;
}
