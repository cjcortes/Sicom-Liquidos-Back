package com.sicom.ms.domain.model.orderstatus;

import reactor.core.publisher.Flux;

public interface OrderStatusOPSimpleGateway {
    Flux<OrderStatusOPSimple> getAll();
}
