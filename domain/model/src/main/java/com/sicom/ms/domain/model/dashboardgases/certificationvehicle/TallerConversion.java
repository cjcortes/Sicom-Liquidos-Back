package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class TallerConversion {
    @JsonProperty("nombre_taller_conversion")
    String nombreTallerConversion;
    @JsonProperty("NIT_taller_conversion")
    String nitTallerConversion;
    @JsonProperty("codigo_sicom_taller_conversion")
    String codigoSicomTallerConversion;
    @JsonProperty("id_taller_conversion")
    IdTallerConversion idTallerConversion;
}

class IdTallerConversion {
    @JsonProperty("id_taller_conversion")
    int idTallerConversion;
}