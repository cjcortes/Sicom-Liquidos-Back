package com.sicom.ms.domain.model.dashboardgases.revisiongarage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RevisionGarage {
    int garageAgentId;
    int garageWfuserId;
    int garageSicomCode;
    String garageTradename;
    String garageAgentSubtype;
    String garageAgentStatus;
}
