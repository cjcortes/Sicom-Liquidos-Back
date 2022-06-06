package com.sicom.ms.infrastructure.sicomapi.agents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentDTO {
    public int idAgente;
    @JsonProperty("Nombre_Comercial")
    public String nombreComercial;
    @JsonProperty("Codigo_Sicom")
    public String codigoSicom;
    @JsonProperty("NIT")
    public String nit;
    @JsonProperty("SubTipoAgente")
    public String subTipoAgente;
    @JsonProperty("TipoAgente")
    public String tipoAgente;
    @JsonProperty("Departamento")
    public String departamento;
    @JsonProperty("Municipio")
    public String municipio;
    @JsonProperty("Direccion_Correspondencia")
    public String direccionCorrespondencia;
    @JsonProperty("Es_Zona_Frontera")
    public boolean zonaFrontera;
    @JsonProperty("Correo_Electronico")
    public String email;
}
