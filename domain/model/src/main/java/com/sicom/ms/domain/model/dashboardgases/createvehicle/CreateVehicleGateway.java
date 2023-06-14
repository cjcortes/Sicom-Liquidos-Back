package com.sicom.ms.domain.model.dashboardgases.createvehicle;

import reactor.core.publisher.Mono;

public interface CreateVehicleGateway {
    Mono<CreateVehicleResponse> createVehicle(CreateVehicleRequest request);
}
