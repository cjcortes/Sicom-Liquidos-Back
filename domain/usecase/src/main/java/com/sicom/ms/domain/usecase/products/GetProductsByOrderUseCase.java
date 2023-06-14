package com.sicom.ms.domain.usecase.products;

import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.products.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProductsByOrderUseCase {

    private final ProductsGateway productsGateway;

    public Flux<Product> getAllByOrderId(String orderId) {
        return productsGateway.getAllByOrderId(orderId);
    }
}
