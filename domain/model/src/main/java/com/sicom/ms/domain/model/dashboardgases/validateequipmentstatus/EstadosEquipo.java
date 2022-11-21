package com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EstadosEquipo {
    @JsonProperty("IdGNVP_EstadoEquipo")
    String idGNVPEstadoEquipo;
    @JsonProperty("nombre_EstadoEquipo")
    String nombreEstadoEquipo;
    @JsonProperty("codigo_EstadoCertificacion")
    String codigoEstadoCertificacion;
}
