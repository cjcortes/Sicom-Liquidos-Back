package com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.util.Error;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ValidateEquipmentExistenceResponse {
    @JsonProperty("value")
    ValueDetail value;
    @JsonProperty("error")
    Error error;
}
