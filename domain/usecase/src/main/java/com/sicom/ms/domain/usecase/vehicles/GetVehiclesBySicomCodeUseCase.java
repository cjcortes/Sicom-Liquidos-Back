package com.sicom.ms.domain.usecase.vehicles;

import com.sicom.ms.domain.model.vehicles.VehicleOpSimple;
import com.sicom.ms.domain.model.vehicles.VehiclesListGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetVehiclesBySicomCodeUseCase {
    private final VehiclesListGateway vehiclesListGateway;

    public Flux<VehicleOpSimple> get(String agentId, boolean acceptOPS) {
        return  vehiclesListGateway.getVehiclesBySicomAgentId(agentId, acceptOPS);
    }

}

