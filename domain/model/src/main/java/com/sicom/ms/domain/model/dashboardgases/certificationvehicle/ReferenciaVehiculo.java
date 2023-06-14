package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ReferenciaVehiculo {
    String seleccionado;
    @JsonProperty("nombre_p_referencia_vehiculo_marca")
    String nombrePReferenciaVehiculoMarca;
    @JsonProperty("nombre_p_referencia_vehiculo_clase")
    String nombrePReferenciaVehiculoClase;
    @JsonProperty("codigo_p_referencia_vehiculo")
    String codigoPReferenciaVehiculo;
    String referencia1;
    String referencia2;
    String referencia3;
    @JsonProperty("nombre_p_referencia_vehiculo_tipo_servicio")
    String nombrePReferenciaVehiculoTipoServicio;
    @JsonProperty("nombre_p_referencia_vehiculo_tipo_combustible")
    String nombrePReferenciaVehiculoTipoCombustible;
    @JsonProperty("id_referencia_vehiculo")
    IdReferenciaVehiculo idReferenciaVehiculo;

}

class IdReferenciaVehiculo{
    @JsonProperty("id_referencia_vehiculo")
    int idReferenciaVehiculo;
    @JsonProperty("id_p_referencia_vehiculo")
    int idPReferenciaVehiculo;
    @JsonProperty("id_p_referencia_vehiculo_marca")
    int idPReferenciaVehiculoMarca;
    @JsonProperty("id_p_referencia_vehiculo_clase")
    int idPReferenciaVehiculoClase;
    @JsonProperty("id_p_referencia_vehiculo_tipo_servicio")
    int idPReferenciaVehiculoTipoServicio;
    @JsonProperty("id_p_referencia_vehiculo_tipo_combustible")
    int idPReferenciaVehiculoTipoCombustible;
}
