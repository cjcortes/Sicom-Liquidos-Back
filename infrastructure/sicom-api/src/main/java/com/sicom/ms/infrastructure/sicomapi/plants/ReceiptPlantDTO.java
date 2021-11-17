package com.sicom.ms.infrastructure.sicomapi.plants;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class SCMMPlanta{
    @JsonProperty("@key")
    public String key;
    public String sEstado;
    @JsonProperty("NombrePlanta")
    public String nombrePlanta;
    public String iCodigoSICOMPlanta;
}

class ColPlanta{
    @JsonProperty("SCMM_Planta")
    public List<SCMMPlanta> sCMM_Planta;
}

class SCMMAgente{
    @JsonProperty("@key")
    public String key;
    public String sNombreComercial;
    public String sCodigoSICOM;
    @JsonProperty("Col_Planta")
    public ColPlanta col_Planta;
}

class Entities{
    @JsonProperty("SCMM_Agente")
    public SCMMAgente sCMM_Agente;
}

public class ReceiptPlantDTO{
    @JsonProperty("Entities")
    public Entities entities;
}
/*

public class ReceiptPlantDTO {
    public int idPlanta;
    @JsonProperty("NombrePlanta")
    public String nombrePlanta;
    @JsonProperty("Estado")
    public String estado;
    public int idAgente;
}*/
