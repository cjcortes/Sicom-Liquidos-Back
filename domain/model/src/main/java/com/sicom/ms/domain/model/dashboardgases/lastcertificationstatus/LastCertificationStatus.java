package com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class LastCertificationStatus {
    int certificationStatusId;
    String certificationStatusName;
    String certificationStatusCode;
}
