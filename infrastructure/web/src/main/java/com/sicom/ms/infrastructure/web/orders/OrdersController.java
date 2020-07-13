package com.sicom.ms.infrastructure.web.orders;

import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.usecase.orders.GetAllOrdersByFilterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final GetAllOrdersByFilterUseCase getAllOrdersByFilterUseCase;

    @GetMapping
    public Flux<Order> getAllByFilter(
            @RequestParam(defaultValue = "-1") String authCode,
            @RequestParam(defaultValue = "-1") String clientCode,
            @RequestParam(defaultValue = "-1") String providerPlantCode,
            @RequestParam(defaultValue = "-1") String orderType,
            @RequestParam(defaultValue = "-1") long suggestedDeliveryStartDate,
            @RequestParam(defaultValue = "-1") long suggestedDeliveryEndDate
    ) {
        var startDate = suggestedDeliveryStartDate == -1 ? null : new Date(suggestedDeliveryStartDate);
        var endDate = suggestedDeliveryEndDate == -1 ? null : new Date(suggestedDeliveryEndDate);
        var orderFilters = OrderFilters.builder()
                .authCode(authCode.trim())
                .clientCode(clientCode.trim())
                .providerPlantCode(providerPlantCode.trim())
                .orderType(orderType.trim())
                .suggestedDeliveryStartDate(startDate)
                .suggestedDeliveryEndDate(endDate)
                .build();
        return getAllOrdersByFilterUseCase.getAllByFilters(orderFilters);
    }
}
