package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UltimaRevision {
    @JsonProperty("nombre_ultimo_tipo_certificacion")
    String nombreUltimoTipoCertificacion;
    @JsonProperty("id_ultimarevision")
    IdUltimaRevision idUltimaRevision;
}

class IdUltimaRevision {
    @JsonProperty("id_ultima_revision")
    int idUltimaRevision;
    @JsonProperty("id_ultimo_tipo_certificacion")
    int idUltimoTipoCertificacion;
}
