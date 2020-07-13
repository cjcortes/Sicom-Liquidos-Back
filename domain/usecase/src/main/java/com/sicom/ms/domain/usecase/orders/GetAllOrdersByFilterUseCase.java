package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.model.orders.OrdersGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.orders.GetAllOrdersByFilterRules.GET_ORDERS_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetAllOrdersByFilterUseCase {

    private final ObjectValidator objectValidator;
    private final OrdersGateway ordersGateway;

    public Flux<Order> getAllByFilters(OrderFilters orderFilters) {
        objectValidator.validate(orderFilters, GET_ORDERS_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getAllByFilters");
        return ordersGateway.getAllByFilters(orderFilters);
    }
}
