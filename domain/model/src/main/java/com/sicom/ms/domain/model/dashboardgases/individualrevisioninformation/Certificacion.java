package com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
public class Certificacion {
    @JsonProperty("codigo_SICOM_Vehiculo")
    int codigoSicomVehiculo;
    @JsonProperty("placa_vehiculo")
    String placaVehiculo;
    @JsonProperty("chip_vehiculo")
    String chipVehiculo;
    @JsonProperty("vin_vehiculo")
    String vinVehiculo;
    @JsonProperty("serial_vehiculo")
    String serialVehiculo;
    @JsonProperty("fecha_proxima_revision_anual")
    String fechaProximaRevisionAnual;
    @JsonProperty("consecutivo_revision_vehiculo")
    int consecutivoRevisionVehiculo;
    @JsonProperty("estado_ultima_certificacion")
    EstadoUltimaCertificacion estadoUltimaCertificacion;
    @JsonProperty("id_certificacionRevInd")
    IdCertificacionRevInd idCertificacionRevInd;
}
