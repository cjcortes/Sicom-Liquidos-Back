package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.util.Error;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CertificationVehicleResponse {
    @JsonProperty("value")
    ValueDetail valueDetail;
    @JsonProperty("error")
    Error error;
}
