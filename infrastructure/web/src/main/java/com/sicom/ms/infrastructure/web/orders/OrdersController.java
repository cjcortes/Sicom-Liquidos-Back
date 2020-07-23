package com.sicom.ms.infrastructure.web.orders;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderDetail;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.usecase.orders.GetAllOrdersByFilterUseCase;
import com.sicom.ms.domain.usecase.orders.GetOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final GetAllOrdersByFilterUseCase getAllOrdersByFilterUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final AuthenticationGateway authenticationGateway;

    @GetMapping
    public Flux<Order> getAllByFilter(
            @RequestParam(defaultValue = "-1") String authCode,
            @RequestParam(defaultValue = "-1") String clientCode,
            @RequestParam(defaultValue = "-1") String providerPlantCode,
            @RequestParam(defaultValue = "-1") String orderType,
            @RequestParam(defaultValue = "-1") long suggestedDeliveryStartDate,
            @RequestParam(defaultValue = "-1") long suggestedDeliveryEndDate,
            Principal principal
    ) {
        return authenticationGateway.getClaims(principal)
                .map(claims -> OrderFilters.builder()
                        .sicomAgent((String) claims.get(SICOM_AGENT))
                        .authCode(authCode.trim())
                        .clientCode(clientCode.trim())
                        .providerPlantCode(providerPlantCode.trim())
                        .orderType(orderType.trim())
                        .suggestedDeliveryStartDate(suggestedDeliveryStartDate)
                        .suggestedDeliveryEndDate(suggestedDeliveryEndDate)
                        .build())
                .flatMapMany(getAllOrdersByFilterUseCase::getAllByFilters);
    }

    @GetMapping(value = "/{id}")
    public Mono<OrderDetail> getById(@PathVariable(value = "id") String id) {
        return getOrderUseCase.getById(id);
    }

}
