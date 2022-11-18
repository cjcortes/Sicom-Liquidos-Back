package com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.util.Error;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class IndividualRevisionInformationResponse {
    @JsonProperty("value")
    ValueDetail value;
    @JsonProperty("error")
    Error error;
}
