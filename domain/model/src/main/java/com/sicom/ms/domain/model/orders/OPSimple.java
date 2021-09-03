package com.sicom.ms.domain.model.orders;

public class OPSimple {
    public Envelope envelope;
}

class SavedMessage{
    public String entities;
}

class ProcessWorkflowClass{
    public int workflowClassId;
    public String workflowClassName;
    public String workflowClassDisplayName;
    public String workflowClassDescription;
    public String workflowClassHelpText;
    public int workflowClassDisplayOrder;
    public String workflowClassCreationDate;
    public String workflowClassGlobalenvelopeForm;
    public String workflowClassAllocationPrinciple;
    public String workflowClassProcessType;
    public String workflowClassDisplay;
    public String workflowClassUseParentRadicationNumber;
}

class ProcessError{
    public String errorCode;
    public String errorMessage;
}

class Task{
    public int taskId;
    public String taskName;
    public String taskDisplayName;
    public String taskDescription;
    public String taskHelpText;
    public int taskEstimatedDuration;
    public String taskType;
    public int taskCost;
    public String taskPriority;
}

class WorkItem{
    public int workItemId;
    public String workItemState;
    public String workItemEntryDate;
    public int workItemDuration;
    public String workItemEstimatedSolutionDate;
    public Task task;
}

class CurrentWorkItems{
    public WorkItem workItem;
}

class Process{
    public int processId;
    public String processRadNumber;
    public SavedMessage savedMessage;
    public ProcessWorkflowClass processWorkflowClass;
    public ProcessError processError;
    public String processGuid;
    public CurrentWorkItems currentWorkItems;
}

class Processes{
    public Process process;
}

class CreateCasesResult{
    public Processes processes;
}

class CreateCasesResponse{
    public CreateCasesResult createCasesResult;
}

class Body{
    public CreateCasesResponse createCasesResponse;
}

class Envelope{
    public Body body;
}

class Root{
    public Envelope envelope;
}


