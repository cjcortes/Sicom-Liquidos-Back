package com.sicom.ms.domain.model.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OPSimple {
    public Process process;
}
class SavedMessage{
    @JsonProperty("Entities")
    public String entities;
}

class ProcessWorkflowClass{
    public String workflowClassId;
    public String workflowClassName;
    public String workflowClassDisplayName;
    public String workflowClassDescription;
    public String workflowClassHelpText;
    public String workflowClassDisplayOrder;
    public String workflowClassCreationDate;
    public String workflowClassGlobalForm;
    public String workflowClassAllocationPrinciple;
    public String workflowClassProcessType;
    public String workflowClassDisplay;
    public String workflowClassUseParentRadicationNumber;
}

class Task{
    public String taskId;
    public String taskName;
    public String taskDisplayName;
    public String taskDescription;
    public String taskHelpText;
    public String taskEstimatedDuration;
    public String taskType;
    public String taskCost;
    public String taskPriority;
}

class WorkItem{
    public String workItemId;
    public String workItemState;
    public String workItemEntryDate;
    public String workItemDuration;
    public String workItemEstimatedSolutionDate;
    public Task task;
}

class CurrentWorkItems{
    public WorkItem workItem;
}

class Process{
    public String processId;
    public String processRadNumber;
    public SavedMessage savedMessage;
    @JsonProperty("ProcessGuid")
    public String processGuid;
    public ProcessWorkflowClass processWorkflowClass;
    @JsonProperty("CurrentWorkItems")
    public CurrentWorkItems currentWorkItems;
}