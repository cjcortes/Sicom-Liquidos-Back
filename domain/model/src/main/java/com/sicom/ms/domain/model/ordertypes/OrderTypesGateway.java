package com.sicom.ms.domain.model.ordertypes;

import reactor.core.publisher.Flux;

public interface OrderTypesGateway {
    Flux<OrderType> getAll(int userCode);
}
