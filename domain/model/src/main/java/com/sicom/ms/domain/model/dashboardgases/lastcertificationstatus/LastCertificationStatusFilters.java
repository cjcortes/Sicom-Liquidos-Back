package com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class LastCertificationStatusFilters {
    String lastCertificationStatusId;
}
