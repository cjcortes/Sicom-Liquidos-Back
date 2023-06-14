package com.sicom.ms.domain.model.orders;

import reactor.core.publisher.Flux;

public interface OrdersModalityTypeGateway {
    Flux<OrderModalityType> getOrderModalityType();
}
