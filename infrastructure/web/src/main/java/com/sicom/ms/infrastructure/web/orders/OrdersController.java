package com.sicom.ms.infrastructure.web.orders;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.orders.*;
import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.usecase.orders.*;
import com.sicom.ms.domain.usecase.products.GetProductsByOrderUseCase;
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
    private final GetProductsByOrderUseCase getProductsByOrderUseCase;
    private final AuthenticationGateway authenticationGateway;
    private final GetCountOrdersStatusUseCase getCountOrdersStatusUseCase;
    private final CreateOPSimpleUseCase createOPSimpleUseCase;
    private final ConfirmOPSimpleUseCase confirmOPSimpleUseCase;

    @GetMapping
    public Flux<Order> getAllByFilter(
            @RequestParam(defaultValue = "-1") String authCode,
            @RequestParam(defaultValue = "-1") String clientCode,
            @RequestParam(defaultValue = "-1") String providerPlantCode,
            @RequestParam(defaultValue = "-1") String orderType,
            @RequestParam(defaultValue = "-1") int orderState,
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
                        .orderState(orderState)
                        .suggestedDeliveryStartDate(suggestedDeliveryStartDate)
                        .suggestedDeliveryEndDate(suggestedDeliveryEndDate)
                        .build())
                .flatMapMany(getAllOrdersByFilterUseCase::getAllByFilters);
    }

    @GetMapping(value = "/{id}")
    public Mono<OrderDetail> getById(@PathVariable(value = "id") String id) {
        return getOrderUseCase.getById(id);
    }

    @GetMapping(value = "/{id}/products")
    public Flux<Product> getProductsByOrderId(@PathVariable(value = "id") String orderId) {
        return getProductsByOrderUseCase.getAllByOrderId(orderId);
    }

    @GetMapping(value = "/status-count")
    public Flux<OrderStatus> getConsumptionsProducts(@RequestParam(defaultValue = "-1") int product,
                                                     @RequestParam(defaultValue = "-1") String orderType,
                                                     @RequestParam(defaultValue = "-1") long startDate,
                                                     @RequestParam(defaultValue = "-1") long endDate,
                                                     Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> OrderStatusFilters.builder()
                        .sicomAgent((String) claims.get(SICOM_AGENT))
                        .product(product)
                        .orderType(orderType)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build())
                .flatMapMany(getCountOrdersStatusUseCase::getCountOrdersStatus);
    }

    @PostMapping(value = "/create-simple-op")
    public Mono<OPSimple> createOpSimple(@RequestBody OPSimpleRequest request) {
        return createOPSimpleUseCase.create(request);
    }

    @PostMapping(value = "/confirm-simple-op")
    public Mono<OPSimplePerform> confirmOpSimple(@RequestBody OPSimpleConfirmRequest request) {
        return confirmOPSimpleUseCase.confirm(request);
    }

}
