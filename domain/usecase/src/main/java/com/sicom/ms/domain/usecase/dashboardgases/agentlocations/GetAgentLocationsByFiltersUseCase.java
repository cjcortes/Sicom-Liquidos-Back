package com.sicom.ms.domain.usecase.dashboardgases.agentlocations;

import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocation;
import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocationFilters;
import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocationsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAgentLocationsByFiltersUseCase {

    private final AgentLocationsGateway agentLocationsGateway;

    public Flux<AgentLocation> getByFilters(AgentLocationFilters agentLocationFilters){
        return agentLocationsGateway.getAgentLocationsByFilters(agentLocationFilters);
    }
}
