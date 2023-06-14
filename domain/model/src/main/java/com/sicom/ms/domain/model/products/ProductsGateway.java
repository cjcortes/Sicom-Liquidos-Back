package com.sicom.ms.domain.model.products;

import reactor.core.publisher.Flux;

public interface ProductsGateway {
    Flux<Product> getAllByOrderId(String orderId);

}
