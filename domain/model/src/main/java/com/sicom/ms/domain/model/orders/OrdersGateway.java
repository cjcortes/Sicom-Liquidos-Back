package com.sicom.ms.domain.model.orders;

import reactor.core.publisher.Flux;

public interface OrdersGateway {
    Flux<Order> getAllByFilters(OrderFilters request);
}
