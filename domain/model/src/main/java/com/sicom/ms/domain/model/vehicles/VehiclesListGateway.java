package com.sicom.ms.domain.model.vehicles;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehiclesListGateway {
    Flux<VehicleOpSimple> getVehiclesBySicomAgentId(String agentId, boolean acceptOPS);
    Mono<VehicleDetail>getVehicle(String param);

}
