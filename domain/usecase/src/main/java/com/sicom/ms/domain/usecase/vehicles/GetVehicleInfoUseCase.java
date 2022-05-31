package com.sicom.ms.domain.usecase.vehicles;

import com.sicom.ms.domain.model.vehicles.VehicleDetail;
import com.sicom.ms.domain.model.vehicles.VehiclesListGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetVehicleInfoUseCase {
    private final VehiclesListGateway vehiclesListGateway;

    public Mono<VehicleDetail> get(String param) {
        return  vehiclesListGateway.getVehicle(param);
    }
}

