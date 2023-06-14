package com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EstadoUltimaCertificacion {
    @JsonProperty("nombre_estado_ultima_certificacion")
    String nombreEstadoUltimaCertificacion;
    @JsonProperty("codigo_estado_ultima_certificacion")
    String codigoEstadoUltimaCertificacion;
    @JsonProperty("id_estado_ultima_certificac")
    IdEstadoUltimaCertificac idEstadoUltimaCertificac;
}

class IdEstadoUltimaCertificac{
    @JsonProperty("id_estado_ultima_certificacion")
    int idEstadoUltimaCertificacion;
}