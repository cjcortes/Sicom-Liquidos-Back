package com.sicom.ms.domain.model.orders;

import reactor.core.publisher.Flux;

public interface OrderTypesGateway {
    Flux<OrderType> getAll(int userCode);
}
