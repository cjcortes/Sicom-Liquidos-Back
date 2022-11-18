package com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class IdCertificacionRevInd {
    @JsonProperty("id_vehiculo_BPM")
    int idVehiculoBPM;
}
