package com.sicom.ms.domain.model.dashboardgases.conversiongarage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ConversionGarageFilters {
    String conversionGarageId;
}
