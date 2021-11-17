package com.sicom.ms.domain.model.plants;

import reactor.core.publisher.Flux;

public interface PlantsGateway {
    Flux<ReceiptPlant> getAllPlants(String agentId);
}
