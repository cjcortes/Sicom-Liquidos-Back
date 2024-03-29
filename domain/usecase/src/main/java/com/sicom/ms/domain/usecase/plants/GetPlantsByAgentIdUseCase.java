package com.sicom.ms.domain.usecase.plants;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.model.plants.PlantsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetPlantsByAgentIdUseCase {
    private final PlantsGateway plantsGateway;

    public Flux<Plant> get(String agentId) {
        return plantsGateway.getPlantsByAgentId(agentId);
    }
}