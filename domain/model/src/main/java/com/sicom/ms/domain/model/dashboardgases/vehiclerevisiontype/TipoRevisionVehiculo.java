package com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TipoRevisionVehiculo {
    @JsonProperty("IdGNVP_TipoCertificacion")
    int idGNVPTipoCertificacion;
    @JsonProperty("nombre_tipoCertificacion")
    String nombreTipoCertificacion;
    @JsonProperty("codigo_tipoCertificacion")
    String codigoTipoCertificacion;
}
