package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ValueDetail {
    @JsonProperty("informacion_vehiculo")
    InformacionVehiculo informacionVehiculo;
    @JsonProperty("ultima_revision")
    UltimaRevision ultimaRevision;
    @JsonProperty("conversion_importacion")
    ConversionImportacion conversionImportacion;
    @JsonProperty("datos_propietario")
    DatosPropietario datosPropietario;
    @JsonProperty("equipos_vehiculos")
    EquiposVehiculos equiposVehiculos;
    @JsonProperty("usuario_registra")
    UsuarioRegistra usuarioRegistra;
    @JsonProperty("agente_registra")
    AgenteRegistra agenteRegistra;
    @JsonProperty("info_vehiculo_registrado_taller")
    InfoVehiculoRegistradoTaller infoVehiculoRegistradoTaller;
}
