package com.sicom.ms.domain.usecase.agents;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.model.agents.AgentsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAgentBySicomCodeUseCase {
    private final AgentsGateway agentsGateway;

    public Flux<Agent> get(String agentId) {
        return agentsGateway.getAgentById(agentId);
    }
}
