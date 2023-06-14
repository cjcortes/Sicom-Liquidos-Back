package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class RevisionTaller {
    @JsonProperty("numero_caso_creacion")
    String numeroCasoCreacion;
    @JsonProperty("registro_taller")
    String registroTaller;
    @JsonProperty("fecha_registro_taller")
    String fechaRegistroTaller;
    @JsonProperty("proviene_plantillas")
    String provienePlantillas;
    @JsonProperty("user_usuario_registra")
    String userUsuarioRegistra;
    @JsonProperty("nombre_comercial_taller_registra")
    String nombreComercialTallerRegistra;
    @JsonProperty("cod_sicom_taller_registra")
    String codSicomTallerRegistra;
    @JsonProperty("nombre_subtipo_agente_taller_registra")
    String nombreSubtipoAgenteTallerRegistra;
    @JsonProperty("cilindros_rev_talleres")
    List<EquipoRevTaller> cilindrosRevTalleres;
    @JsonProperty("reguladores_rev_talleres")
    List<EquipoRevTaller> reguladoresRevTalleres;
    @JsonProperty("id_revision_taller")
    IdRevisionTaller idRevisionTaller;
}

class IdRevisionTaller {
    @JsonProperty("id_revision_taller")
    int idRevisionTaller;
    @JsonProperty("id_vehiculo_BPM")
    int idVehiculoBPM;
    @JsonProperty("id_usuario_registra")
    int idUsuarioRegistra;
    @JsonProperty("id_taller_registra")
    int idTallerRegistra;
    @JsonProperty("id_subtipo_agente_taller_registra")
    int idSubtipoAgenteTallerRegistra;
}
