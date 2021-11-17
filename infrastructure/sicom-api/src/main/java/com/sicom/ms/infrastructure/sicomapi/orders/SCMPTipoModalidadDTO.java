package com.sicom.ms.infrastructure.sicomapi.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SCMPTipoModalidadDTO {
    @JsonProperty("@key")
    public String key;
    public String sNombre;
    public String sCodigo;
}
