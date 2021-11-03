package com.sicom.ms.infrastructure.web.agents;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.usecase.agents.GetAgentsUseCase;
import com.sicom.ms.domain.usecase.plants.GetAllPlantsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
public class AgentsController {
    private final GetAgentsUseCase getAgentsUseCase;

    @GetMapping(value = "/get-agents")
    public Flux<Agent> getAllPlants() {
        return getAgentsUseCase.get();
    }
}
