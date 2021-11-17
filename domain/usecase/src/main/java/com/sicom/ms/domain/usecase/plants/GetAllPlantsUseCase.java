package com.sicom.ms.domain.usecase.plants;

import com.sicom.ms.domain.model.plants.ReceiptPlant;
import com.sicom.ms.domain.model.plants.PlantsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllPlantsUseCase {
    private final PlantsGateway plantsGateway;

    public Flux<ReceiptPlant> get(String agentId) {
        return plantsGateway.getAllPlants(agentId);
    }
}

