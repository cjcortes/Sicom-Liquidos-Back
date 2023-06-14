package com.sicom.ms.domain.model.dashboardgases.measurementunit;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class MeasurementUnit {
    int measurementUnitId;
    String measurementUnitName;
    String measurementUnitCode;
}
