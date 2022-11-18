package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class AgenteRegistra {
    @JsonProperty("id_agente_registra")
    IdAgenteRegistra idAgenteRegistra;
    @JsonProperty("codigo_sicom")
    String codigoSicom;
    @JsonProperty("nombre_subtipo_agente")
    String nombreSubtipoAgente;
}

class IdAgenteRegistra {
    @JsonProperty("id_agente_registra")
    int idAgenteRegistra;
    @JsonProperty("id_subtipoagente")
    int idSubtipoAgente;
}
