package com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class VehicleRevisionTypeFilters {
    String placa;
    String vin;
    String chip;
}
