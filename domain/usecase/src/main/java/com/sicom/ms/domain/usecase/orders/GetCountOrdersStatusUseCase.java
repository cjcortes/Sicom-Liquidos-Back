package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.consumptions.ConsumptionProduct;
import com.sicom.ms.domain.model.consumptions.ConsumptionsProductsFilters;
import com.sicom.ms.domain.model.orderinfo.OrderInfoGateway;
import com.sicom.ms.domain.model.orders.OrderStatus;
import com.sicom.ms.domain.model.orders.OrderStatusFilters;
import com.sicom.ms.domain.model.orders.OrdersStatusGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetCountOrdersStatusUseCase {
    private final OrdersStatusGateway ordersStatusGateway;

    public Flux<OrderStatus> getCountOrdersStatus(OrderStatusFilters orderStatusFilters) {
        return ordersStatusGateway.getCountOrdersStatus(orderStatusFilters);
    }
}
