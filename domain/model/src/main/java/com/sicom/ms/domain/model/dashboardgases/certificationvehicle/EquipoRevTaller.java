package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EquipoRevTaller {
    @JsonProperty("numero_caso_creacion")
    String numeroCasoCreacion;
    @JsonProperty("vehiculo_copia_serial")
    String vehiculoCopiaSerial;
    @JsonProperty("vehiculo_copia_marca")
    String vehiculoCopiaMarca;
    @JsonProperty("vehiculo_copia_modelo")
    String vehiculoCopiaModelo;
    @JsonProperty("vehiculo_copia_fecha_fabricacion")
    String vehiculoCopiaFechaFabricacion;
    @JsonProperty("vehiculo_copia_fecha_vencimiento")
    String vehiculoCopiaFechaVencimiento;
    @JsonProperty("vehiculo_copia_fecha_prox_ph")
    String vehiculoCopiaFechaProx_ph;
    @JsonProperty("nombre_estado_equipo")
    String nombreEstadoEquipo;
    @JsonProperty("nombre_tipo_equipo")
    String nombreTipoEquipo;
    @JsonProperty("id_cilindro_rev_talleres")
    IdCilindroRev idCilindroRevTalleres;
}

class IdCilindroRev {
    @JsonProperty("id_revision_taller")
    int idRevisionTaller;
    @JsonProperty("id_vehiculo_BPM")
    int idVehiculoBPM;
    @JsonProperty("id_vehiculo_equipo_copia")
    int idVehiculoEquipoCopia;
    @JsonProperty("id_estado_equipo")
    int idEstadoEquipo;
    @JsonProperty("id_tipo_equipo")
    int idTipoEquipo;
}
