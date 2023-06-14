package com.sicom.ms.domain.model.dashboardgases.agentlocations;

import reactor.core.publisher.Flux;

public interface AgentLocationsGateway {
    Flux<AgentLocation> getAgentLocationsByFilters (AgentLocationFilters request);
}
