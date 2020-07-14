package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.common.TimeProvider;
import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.model.orders.OrdersGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.temporal.ChronoUnit;

import static com.sicom.ms.domain.usecase.orders.GetAllOrdersByFilterRules.GET_ORDERS_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetAllOrdersByFilterUseCase {

    private final ObjectValidator objectValidator;
    private final OrdersGateway ordersGateway;
    private final TimeProvider timeProvider;

    public Flux<Order> getAllByFilters(OrderFilters orderFilters) {
        objectValidator.validate(orderFilters, GET_ORDERS_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getAllByFilters");
        if (orderFilters.getSuggestedDeliveryStartDate() == -1) {
            orderFilters = loadDates(orderFilters);
        }
        return ordersGateway.getAllByFilters(orderFilters);
    }

    private OrderFilters loadDates(OrderFilters orderFilters) {
        return orderFilters.toBuilder()
                .suggestedDeliveryStartDate(timeProvider.currentDateMinus(30, ChronoUnit.DAYS).getTime())
                .suggestedDeliveryEndDate(timeProvider.currentDate().getTime())
                .build();
    }
}
