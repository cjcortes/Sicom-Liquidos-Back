package com.sicom.ms.domain.model.dashboardgases.garagecoordinates;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.util.Error;
import lombok.Builder;
import lombok.Value;
@Value
@Builder(toBuilder = true)
public class GarageCoordinatesResponse {
    @JsonProperty("value")
    ValueDetail value;
    Error error;
}

