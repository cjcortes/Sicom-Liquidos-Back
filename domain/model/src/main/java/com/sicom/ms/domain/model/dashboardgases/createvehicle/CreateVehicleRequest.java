package com.sicom.ms.domain.model.dashboardgases.createvehicle;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CreateVehicleRequest {
    InformacionCaso informacionCaso;
    GestionVehiculosGNCV gestionVehiculosGNCV;
}
