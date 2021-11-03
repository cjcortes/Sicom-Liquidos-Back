package com.sicom.ms.domain.model.agents;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Agent {

    public int idAgente;
    public String nombreComercial;
    public String codigoSicom;

}
