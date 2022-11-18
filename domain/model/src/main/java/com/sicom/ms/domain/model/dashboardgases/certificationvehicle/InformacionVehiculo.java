package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class InformacionVehiculo {
    @JsonProperty("vehiculo_nuevo")
    String vehiculoNuevo;
    @JsonProperty("informacion_general_vehiculo")
    InformacionGeneralVehiculo informacionGeneralVehiculo;
    @JsonProperty("vehiculo_especial")
    VehiculoEspecial vehiculoEspecial;
}
