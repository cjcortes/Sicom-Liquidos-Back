package com.sicom.ms.domain.model.orders;

import reactor.core.publisher.Mono;

public interface OrdersModalityTypeGateway {
    Mono<OrderModalityTypeEntitie> getOrderModalityType();
}
