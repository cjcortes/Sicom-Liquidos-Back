package com.sicom.ms.domain.model.dashboardgases.revisiontype;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RevisionTypeFilters {
    String revisionTypeId;
}
