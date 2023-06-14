package com.sicom.ms.domain.model.dashboardgases.fueltypeparametric;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class FuelTypeParametricFilters {
    String fuelTypeId;
}
