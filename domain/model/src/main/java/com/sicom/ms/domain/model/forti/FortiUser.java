package com.sicom.ms.domain.model.forti;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class FortiUser {
        @JsonProperty("username")
        String userName;
        @JsonProperty("token_auth")
        boolean authActive;
}
