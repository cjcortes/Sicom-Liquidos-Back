package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orderinfo.OrderInfoGateway;
import com.sicom.ms.domain.model.orders.OrderDetail;
import com.sicom.ms.domain.model.providerscustomers.ProvidersCustomersGateway;
import com.sicom.ms.domain.model.vehicles.VehiclesGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetOrderUseCase {

    private final OrderInfoGateway orderInfoGateway;
    private final ProvidersCustomersGateway providersCustomersGateway;
    private final VehiclesGateway vehiclesGateway;

    public Mono<OrderDetail> getById(String id) {
        return Mono.zip(orderInfoGateway.getByOrderId(id),
                providersCustomersGateway.getByOrderId(id),
                vehiclesGateway.getByOrderId(id))
                .map(t -> OrderDetail.builder()
                        .orderInfo(t.getT1())
                        .providerCustomer(t.getT2())
                        .vehicle(t.getT3())
                        .build());
    }
}
