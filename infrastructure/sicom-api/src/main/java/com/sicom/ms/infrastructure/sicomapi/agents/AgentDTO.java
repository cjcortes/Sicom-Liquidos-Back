package com.sicom.ms.infrastructure.sicomapi.agents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentDTO {
    public int idAgente;
    @JsonProperty("Nombre_Comercial")
    public String nombreComercial;
    @JsonProperty("Codigo_Sicom")
    public String codigoSicom;
}
