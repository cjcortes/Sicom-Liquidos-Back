package com.sicom.ms.domain.usecase.dashboardgases.createvehicle;

import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleGateway;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleRequest;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateVehicleUseCase {

    private final CreateVehicleGateway createVehicleGateway;

    public Mono<CreateVehicleResponse> create(CreateVehicleRequest request){
        return createVehicleGateway.createVehicle(request);
    }
}
