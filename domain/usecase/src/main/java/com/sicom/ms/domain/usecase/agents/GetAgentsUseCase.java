package com.sicom.ms.domain.usecase.agents;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.model.agents.AgentsGateway;
import com.sicom.ms.domain.model.plants.Plant;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAgentsUseCase {
    private final AgentsGateway agentsGateway;

    public Flux<Agent> get() {
        return agentsGateway.getAllAgents();
    }
}
