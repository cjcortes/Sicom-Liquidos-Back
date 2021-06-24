package com.sicom.ms.infrastructure.web.products;

import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.products.ProductMaster;
import com.sicom.ms.domain.usecase.products.ProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsUseCase productsUseCase;

    @GetMapping(value = "/list")
    public Flux<ProductMaster> getAllProducts() {
        return productsUseCase.getAllProducts();
    }
}
