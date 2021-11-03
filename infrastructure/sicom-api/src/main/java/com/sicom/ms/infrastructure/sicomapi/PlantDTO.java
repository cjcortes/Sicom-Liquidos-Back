package com.sicom.ms.infrastructure.sicomapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlantDTO {
    public int idPlanta;
    @JsonProperty("NombrePlanta")
    public String nombrePlanta;
    @JsonProperty("Estado")
    public String estado;
    public int idAgente;
}
