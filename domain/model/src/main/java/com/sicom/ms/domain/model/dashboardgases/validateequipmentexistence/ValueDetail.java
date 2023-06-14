package com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.util.EquipoVehiculo;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ValueDetail {
    @JsonProperty("equipo")
    EquipoVehiculo equipo;
}
