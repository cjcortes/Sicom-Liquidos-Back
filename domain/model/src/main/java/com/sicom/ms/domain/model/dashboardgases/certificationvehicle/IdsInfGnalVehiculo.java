package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class IdsInfGnalVehiculo {
    @JsonProperty("id_vehiculo_BPM")
    int idVehiculoBPM;
    @JsonProperty("id_marca_vehiculo")
    int idMarcaVehiculo;
    @JsonProperty("id_convertido_dedicado")
    int idConvertidoDedicado;
    @JsonProperty("id_tipo_combustible")
    int idTipoCombustible;
    @JsonProperty("id_clase_vehiculo")
    int idClaseVehiculo;
    @JsonProperty("id_modelo_ano_vehiculo")
    int idModeloAnioVehiculo;
    @JsonProperty("id_tipo_servicio_vehiculo")
    int idTipoServicioVehiculo;
    @JsonProperty("id_estado_vehiculo")
    int idEstadoVehiculo;
    @JsonProperty("id_dual_exclusivo")
    int idDualExclusivo;
}
