package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.List;

@Value
public class InfoVehiculoRegistradoTaller {
    @JsonProperty("revisiones_talleres")
    List<RevisionTaller> revisionesTalleres;
}
