package com.sicom.ms.domain.model.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OPSProcessError {
    public String processId;
    public String processRadNumber;
    public OPPSError processError;
    @JsonProperty("CurrentWorkItems")
    public String currentWorkItems;
}
