package com.sicom.ms.infrastructure.web.plants;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.usecase.plants.GetPlantsByAgentIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantsController {
    private final GetPlantsByAgentIdUseCase getPlantsByAgentIdUseCase;

    @GetMapping()
    public Flux<Plant> get(@RequestParam  String agentId) {
        return getPlantsByAgentIdUseCase.get(agentId);
    }
}
