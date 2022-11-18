package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.util.EquipoVehiculo;

import java.util.List;

public class EquiposVehiculos {
    @JsonProperty("cilindros_vehiculos")
    List<EquipoVehiculo> cilindrosVehiculos;
    @JsonProperty("reguladores_vehiculos")
    List<EquipoVehiculo> reguladoresVehiculos;
}
