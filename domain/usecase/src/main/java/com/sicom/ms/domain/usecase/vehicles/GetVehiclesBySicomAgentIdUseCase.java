package com.sicom.ms.domain.usecase.vehicles;

import com.sicom.ms.domain.model.plants.PlantsGateway;
import com.sicom.ms.domain.model.vehicles.Vehicles;
import com.sicom.ms.domain.model.vehicles.VehiclesListGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetVehiclesBySicomAgentIdUseCase {
    private final VehiclesListGateway vehiclesListGateway;

    public Flux<Vehicles> getVehiclesBySicomAgentId(String agentId) {
        return  vehiclesListGateway.getVehiclesBySicomAgentId(agentId);
    }

}

