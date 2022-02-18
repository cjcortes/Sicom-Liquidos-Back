package com.sicom.ms.domain.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorRoot {
    @JsonProperty("Error")
    public Error error;
}

