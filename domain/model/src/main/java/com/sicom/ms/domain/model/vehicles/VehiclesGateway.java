package com.sicom.ms.domain.model.vehicles;

import reactor.core.publisher.Mono;

public interface VehiclesGateway {
    Mono<Vehicle> getByOrderId(String orderId);
}
