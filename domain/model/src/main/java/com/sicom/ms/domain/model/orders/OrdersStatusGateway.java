package com.sicom.ms.domain.model.orders;

import reactor.core.publisher.Flux;

public interface OrdersStatusGateway {
    Flux<OrderStatus> getCountOrdersStatus(OrderStatusFilters orderStatusFilters);
}
