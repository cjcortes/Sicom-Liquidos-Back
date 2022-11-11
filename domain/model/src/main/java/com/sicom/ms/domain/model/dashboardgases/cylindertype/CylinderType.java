package com.sicom.ms.domain.model.dashboardgases.cylindertype;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CylinderType {
    int cylinderTypeId;
    String cylinderTypeName;
    String cylinderTypeCode;
}
