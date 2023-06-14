package com.sicom.ms.domain.model.dashboardgases.confirmequipment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.util.Error;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ConfirmEquipmentResponse {
    @JsonProperty("value")
    ValueDetail value;
    @JsonProperty("error")
    Error error;
}
