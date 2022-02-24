package com.sicom.ms.domain.model.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Process {
    public String processId;
    public String processRadNumber;
    public SavedMessage savedMessage;
    @JsonProperty("ProcessGuid")
    public String processGuid;
    public ProcessWorkflowClass processWorkflowClass;
    public ProcessError processError;
    @JsonProperty("CurrentWorkItems")
    public CurrentWorkItems currentWorkItems;
}
