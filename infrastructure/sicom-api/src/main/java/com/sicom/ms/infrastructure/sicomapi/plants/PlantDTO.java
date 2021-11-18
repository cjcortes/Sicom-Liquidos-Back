package com.sicom.ms.infrastructure.sicomapi.plants;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlantDTO {
    public int idPlanta;
    @JsonProperty("NombrePlanta")
    public String nombrePlanta;
    @JsonProperty("Estado")
    public String estado;
    public int idAgente;
    @JsonProperty("Capacidad_Total_Nominal")
    public double capacidad_Total_Nominal;
    @JsonProperty("Capacidad_Total_Operativa")
    public double capacidad_Total_Operativa;
}

