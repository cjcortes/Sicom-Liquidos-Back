package com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TotalGarageRevision {

    String name;
    String value;
    int numericValue;
}
