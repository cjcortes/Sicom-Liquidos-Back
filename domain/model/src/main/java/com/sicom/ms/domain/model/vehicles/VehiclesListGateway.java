package com.sicom.ms.domain.model.vehicles;

import reactor.core.publisher.Flux;

public interface VehiclesListGateway {
    Flux<Vehicles> getVehiclesBySicomAgentId(String agentId);
}
