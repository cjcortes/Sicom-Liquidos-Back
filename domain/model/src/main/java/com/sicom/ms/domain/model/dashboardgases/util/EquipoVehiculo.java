package com.sicom.ms.domain.model.dashboardgases.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EquipoVehiculo {
    @JsonProperty("placa_vehiculo")
    String placaVehiculo;
    @JsonProperty("vin_vehiculo")
    String vinVehiculo;
    @JsonProperty("chip_vehiculo")
    String chipVehiculo;
    @JsonProperty("esta_libre")
    String estaLibre;
    @JsonProperty("capacidad")
    String capacidad;
    @JsonProperty("nombre_unidad_medida")
    String nombreUnidadMedida;
    @JsonProperty("fecha_fabricacion")
    String fechaFabricacion;
    @JsonProperty("fecha_vencimiento")
    String fechaVencimiento;
    @JsonProperty("fecha_proxima_revision")
    String fechaProximaRevision;
    @JsonProperty("nombre_marca_equipo")
    String nombreMarcaEquipo;
    @JsonProperty("nombre_modelo_equipo")
    String nombreModeloEquipo;
    @JsonProperty("equipo_placa")
    String equipoPlaca;
    @JsonProperty("equipo_serial")
    String equipoSerial;
    @JsonProperty("equipo_vin")
    String equipoVin;
    @JsonProperty("nombre_tipo_cilindro")
    String nombreTipoCilindro;
    @JsonProperty("nombre_tipo_equipo")
    String nombreTipoEquipo;
    @JsonProperty("nombre_estado_equipo")
    String nombreEstadoEquipo;
    @JsonProperty("registro_antiguo")
    String registroAntiguo;
    @JsonProperty("intervalo_revision")
    String intervaloRevision;
    @JsonProperty("nombre_ultimo_estado_equipo")
    String nombreUltimoEstadoEquipo;
    @JsonProperty("id_cilindro")
    IdEquipo idCilindro;
}

class IdEquipo {
    @JsonProperty("id_equipo")
    int idEquipo;
    @JsonProperty("id_vehiculo_equipo")
    int idVehiculoEquipo;
    @JsonProperty("id_vehiculo_BPM")
    int idVehiculoBPM;
    @JsonProperty("id_marca_equipo")
    int idMarcaEquipo;
    @JsonProperty("id_modelo_equipo")
    int idModeloEquipo;
    @JsonProperty("id_tipo_cilindro")
    int idTipoCilindro;
    @JsonProperty("id_tipo_equipo")
    int idTipoEquipo;
    @JsonProperty("id_unidad_medida")
    int idUnidadMedida;
    @JsonProperty("id_estado_equipo")
    int idEstadoEquipo;
    @JsonProperty("id_ultimo_estado_equipo")
    int idUltimoEstadoEquipo;
}
