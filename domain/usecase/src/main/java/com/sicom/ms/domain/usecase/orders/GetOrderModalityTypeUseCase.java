package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.OrderModalityType;
import com.sicom.ms.domain.model.orders.OrdersModalityTypeGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetOrderModalityTypeUseCase {
    private final OrdersModalityTypeGateway ordersModalityTypeGateway;

    public Flux<OrderModalityType> getOrderModalityType() {
        return ordersModalityTypeGateway.getOrderModalityType();
    }
}
