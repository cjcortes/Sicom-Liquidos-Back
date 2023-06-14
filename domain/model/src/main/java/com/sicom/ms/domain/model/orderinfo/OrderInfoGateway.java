package com.sicom.ms.domain.model.orderinfo;

import reactor.core.publisher.Mono;

public interface OrderInfoGateway {
    Mono<OrderInfo> getByOrderId(String orderId);
}
