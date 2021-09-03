package com.sicom.ms.domain.model.orders;

public class OPSimplePerform {
    public EnvelopePerform envelope;
}

class SavedMessagePerform{
    public String entities;
}

class ProcessWorkflowClassPerform{
    public int workflowClassId;
    public String workflowClassName;
    public String workflowClassDisplayName;
    public String workflowClassDescription;
    public String workflowClassHelpText;
    public int workflowClassDisplayOrder;
    public String workflowClassCreationDate;
    public String workflowClassGlobalForm;
    public String workflowClassAllocationPrinciple;
    public String workflowClassProcessType;
    public String workflowClassDisplay;
    public String workflowClassUseParentRadicationNumber;
}

class ProcessErrorPerform{
    public String errorCode;
    public String errorMessage;
}

class TaskPerform{
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

class WorkItemPerform{
    public int workItemId;
    public String workItemState;
    public String workItemEntryDate;
    public int workItemDuration;
    public String workItemEstimatedSolutionDate;
    public TaskPerform task;
}

class CurrentWorkItemsPerform{
    public WorkItemPerform workItem;
}

class ProcessPerform{
    public int processId;
    public String processRadNumber;
    public SavedMessagePerform savedMessage;
    public ProcessWorkflowClassPerform processWorkflowClass;
    public ProcessErrorPerform processError;
    public String processGuid;
    public CurrentWorkItemsPerform currentWorkItems;
}

class ProcessesPerform{
    public ProcessPerform process;
}

class PerformActivityResult{
    public ProcessesPerform processes;
}

class PerformActivityResponse{
    public PerformActivityResult performActivityResult;
}

class BodyPerform{
    public PerformActivityResponse performActivityResponse;
}

class EnvelopePerform{
    public BodyPerform body;
}

