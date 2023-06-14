package com.sicom.ms.domain.usecase.products;

import com.sicom.ms.domain.model.orderinfo.OrderInfoGateway;
import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.products.ProductsGateway;
import com.sicom.ms.domain.usecase.orders.GetOrderUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductsByOrderUseCaseTest {

    @Mock
    private ProductsGateway productsGateway;

    @InjectMocks
    private GetProductsByOrderUseCase getProductsByOrderUseCase;

    @Test
    void getAllByOrderIdShouldReturnProductsFromRepository() {

        var response = Collections.singletonList(Product.builder().build());

        when(productsGateway.getAllByOrderId("1"))
                .thenReturn(Flux.fromIterable(response));

        StepVerifier.create(getProductsByOrderUseCase.getAllByOrderId("1"))
                .expectNextSequence(response)
                .verifyComplete();

        verify(productsGateway).getAllByOrderId("1");
    }
}
