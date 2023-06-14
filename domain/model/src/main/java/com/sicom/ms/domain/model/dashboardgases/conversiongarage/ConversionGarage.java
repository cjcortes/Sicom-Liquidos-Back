package com.sicom.ms.domain.model.dashboardgases.conversiongarage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ConversionGarage {
    int conversionGarageId;
    String revisionGarageName;
    String garageNit;
    int garageSicomCode;
    int garageAgentMasterId;
}
