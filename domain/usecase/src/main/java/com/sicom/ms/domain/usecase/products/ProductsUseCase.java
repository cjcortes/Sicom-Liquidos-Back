package com.sicom.ms.domain.usecase.products;

import com.sicom.ms.domain.model.products.ProductMaster;
import com.sicom.ms.domain.model.products.ProductsMasterGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ProductsUseCase {
    private final ProductsMasterGateway productsMasterGateway;

    public Flux<ProductMaster> getAllProducts(String sicomAgent) {
        return productsMasterGateway.getAllProducts(sicomAgent);
    }
}
