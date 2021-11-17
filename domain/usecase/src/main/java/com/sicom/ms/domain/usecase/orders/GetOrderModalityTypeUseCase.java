package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.OrderModalityTypeEntitie;
import com.sicom.ms.domain.model.orders.OrdersModalityTypeGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetOrderModalityTypeUseCase {
    private final OrdersModalityTypeGateway ordersModalityTypeGateway;

    public Mono<OrderModalityTypeEntitie> getOrderModalityType() {
        return ordersModalityTypeGateway.getOrderModalityType();
    }
}
