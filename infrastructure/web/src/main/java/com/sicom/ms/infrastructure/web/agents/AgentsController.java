package com.sicom.ms.infrastructure.web.agents;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.usecase.agents.GetAgentByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentsController {
    private final GetAgentByIdUseCase getAgentByIdUseCase;

    @GetMapping()
    public Flux<Agent> getAgents(@RequestParam String codigoSicom) {
        return getAgentByIdUseCase.get(codigoSicom);
    }
}
