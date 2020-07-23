package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orderinfo.OrderInfo;
import com.sicom.ms.domain.model.orderinfo.OrderInfoGateway;
import com.sicom.ms.domain.model.providerscustomers.ProviderCustomer;
import com.sicom.ms.domain.model.providerscustomers.ProvidersCustomersGateway;
import com.sicom.ms.domain.model.vehicles.Vehicle;
import com.sicom.ms.domain.model.vehicles.VehiclesGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetOrderUseCaseTest {

    @Mock
    private OrderInfoGateway orderInfoGateway;
    @Mock
    private ProvidersCustomersGateway providersCustomersGateway;
    @Mock
    private VehiclesGateway vehiclesGateway;

    @InjectMocks
    private GetOrderUseCase getOrderUseCase;

    @Test
    void getAllByFiltersShouldReturnOrdersFromRepository() {

        var orderData = OrderInfo.builder().build();
        var providerCustomer = ProviderCustomer.builder().build();
        var vehicle = Vehicle.builder().build();

        when(orderInfoGateway.getByOrderId("1"))
                .thenReturn(Mono.just(orderData));
        when(providersCustomersGateway.getByOrderId("1"))
                .thenReturn(Mono.just(providerCustomer));
        when(vehiclesGateway.getByOrderId("1"))
                .thenReturn(Mono.just(vehicle));

        StepVerifier.create(getOrderUseCase.getById("1"))
                .consumeNextWith(orderDetail -> {
                    assertThat(orderDetail.getOrderInfo()).isEqualTo(orderData);
                    assertThat(orderDetail.getProviderCustomer()).isEqualTo(providerCustomer);
                    assertThat(orderDetail.getVehicle()).isEqualTo(vehicle);
                })
                .verifyComplete();

        verify(orderInfoGateway).getByOrderId("1");
        verify(providersCustomersGateway).getByOrderId("1");
        verify(vehiclesGateway).getByOrderId("1");
    }
}
