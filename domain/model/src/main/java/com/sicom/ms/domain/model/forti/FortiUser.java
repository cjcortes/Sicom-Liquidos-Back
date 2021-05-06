package com.sicom.ms.domain.model.forti;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class FortiUser {
        @JsonProperty("user_name")
        String userName;
        @JsonProperty("auth_active")
        boolean authActive;
}
