package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class InformacionGeneralVehiculo {
    @JsonProperty("placa_vehiculo")
    String placaVehiculo;
    @JsonProperty("vin_vehiculo")
    String vinVehiculo;
    @JsonProperty("nombre_marca_vehiculo")
    String nombreMarcaVehiculo;
    @JsonProperty("nombre_convertido_dedicado_vehiculo")
    String nombreConvertidoDedicadoVehiculo;
    @JsonProperty("nombre_tipo_combustible_vehiculo")
    String nombreTipoCombustibleVehiculo;
    @JsonProperty("nombre_clase_vehiculo")
    String nombreClaseVehiculo;
    @JsonProperty("nombre_modelo_ano_vehiculo")
    String nombreModeloAnioVehiculo;
    @JsonProperty("nombre_tipo_servicio_vehiculo")
    String nombreTipoServicioVehiculo;
    @JsonProperty("nombre_estado_vehiculo")
    String nombreEstadoVehiculo;
    @JsonProperty("chip_vehiculo")
    String chipVehiculo;
    @JsonProperty("capacidad_total_cilindros")
    String capacidadTotalCilindros;
    @JsonProperty("capacidad_total_cilindros_m3")
    String capacidadTotalCilindrosM3;
    @JsonProperty("nombre_dual_exclusivo")
    String nombreDualExclusivo;
    @JsonProperty("ruta_tarjeta_propiedad")
    String rutaTarjetaPropiedad;
    @JsonProperty("referencia_vehiculo")
    ReferenciaVehiculo referenciaVehiculo;
    @JsonProperty("ids_inf_gnal_vehiculo")
    IdsInfGnalVehiculo idsInfGnalVehiculo;
}
