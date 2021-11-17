package com.sicom.ms.infrastructure.web.agents;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.usecase.agents.GetAgentByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentsController {
    private final GetAgentByIdUseCase getAgentByIdUseCase;

    @GetMapping(value = "/get-agent/{id}")
    public Flux<Agent> getAgents(@PathVariable(value = "id") String agentId) {
        return getAgentByIdUseCase.get(agentId);
    }
}
