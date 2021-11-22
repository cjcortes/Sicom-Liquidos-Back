package com.sicom.ms.domain.model.agents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Agent {

    public int agentId;
    public String commercialName;
    public String sicomCode;
    public String nit;
    public String agentSubType;
    public String agentType;
    public String department;
    public String municipality;
    public String addressCorrespondence;
    public boolean isFrontierZone;
}
