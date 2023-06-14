package com.sicom.ms.domain.model.agents;

import reactor.core.publisher.Flux;

public interface AgentsGateway {
    Flux<Agent> getAgentById(String agentId);
}
