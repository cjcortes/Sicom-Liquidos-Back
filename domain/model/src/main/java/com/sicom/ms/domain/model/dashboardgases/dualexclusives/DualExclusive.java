package com.sicom.ms.domain.model.dashboardgases.dualexclusives;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DualExclusive {
    int dualExclusiveId;
    String dualExclusiveName;
    String dualExclusiveCode;
}
