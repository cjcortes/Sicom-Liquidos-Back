package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CertificationVehicleFilters {
    String placa;
    String vin;
    String chip;
}
