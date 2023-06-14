package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ConversionImportacion {
    @JsonProperty("fecha_conversion_importacion")
    String fechaConversionImportacion;
    @JsonProperty("taller_conversion")
    TallerConversion tallerConversion;
    @JsonProperty("NIT_taller_conversion")
    String nitTallerConversion;
    @JsonProperty("NIT_con_dv_taller")
    String nitConDvTaller;
    @JsonProperty("id_conversion_importacion")
    IdConversionImportacion idConversionImportacion;
}

class IdConversionImportacion {
    @JsonProperty("id_conversion_importacion")
    int idConversionImportacion;
}
