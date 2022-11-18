package com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class ValueDetail {
    @JsonProperty("TipoRevisionVehiculo")
    List<TipoRevisionVehiculo> tipoRevisionVehiculo;
}
