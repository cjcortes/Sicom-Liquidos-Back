package com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleYearModel {
    int yearModelId;
    String yearModel;
    int yearCode;
}
