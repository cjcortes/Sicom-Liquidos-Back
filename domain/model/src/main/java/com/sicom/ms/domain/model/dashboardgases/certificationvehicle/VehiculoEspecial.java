package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class VehiculoEspecial {
    @JsonProperty("revision_inicial")
    String revisionInicial;
    @JsonProperty("es_vehiculo_especial")
    String esVehiculoEspecial;
    String serial;
}
