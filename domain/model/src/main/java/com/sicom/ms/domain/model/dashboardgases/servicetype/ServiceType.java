package com.sicom.ms.domain.model.dashboardgases.servicetype;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ServiceType {
    int serviceTypeId;
    String serviceTypeName;
    String serviceTypeCode;
}
