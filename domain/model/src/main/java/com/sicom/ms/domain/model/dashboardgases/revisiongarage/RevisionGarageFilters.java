package com.sicom.ms.domain.model.dashboardgases.revisiongarage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RevisionGarageFilters {
    String revisionGarageId;
}
