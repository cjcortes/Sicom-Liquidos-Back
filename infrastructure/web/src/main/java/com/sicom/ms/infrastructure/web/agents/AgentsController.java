package com.sicom.ms.infrastructure.web.agents;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.usecase.agents.GetAgentBySicomCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentsController {
    private final GetAgentBySicomCodeUseCase getAgentBySicomCodeUseCase;

    @GetMapping()
    public Flux<Agent> getAgents(@RequestParam String sicomCode) {
        return getAgentBySicomCodeUseCase.get(sicomCode);
    }
}
