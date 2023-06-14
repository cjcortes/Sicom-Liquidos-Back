package com.sicom.ms.domain.model.dashboardgases.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Process {
    public String processId;
    public String processRadNumber;
    public ProcessError processError;
    @JsonProperty("CurrentWorkItems")
    public String currentWorkItems;
}