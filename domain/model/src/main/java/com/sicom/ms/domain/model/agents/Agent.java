package com.sicom.ms.domain.model.agents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Agent {

    public int idAgente;
    public String nombreComercial;
    public String codigoSicom;
    public String nit;
    public String subTipoAgente;
    public String tipoAgente;
    public String departamento;
    public String municipio;
    public String direccionCorrespondencia;
    public boolean zonaFrontera;
}
