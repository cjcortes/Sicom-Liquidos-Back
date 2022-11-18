package com.sicom.ms.domain.model.dashboardgases.createvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class GestionVehiculosGNCV {
    @JsonProperty("idM_Vehiculo")
    IdMVehiculo idMVehiculo;
    @JsonProperty("idP_RespuestaGestion")
    int idPRespuestaGestion;
}
