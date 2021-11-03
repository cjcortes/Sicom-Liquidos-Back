package com.sicom.ms.infrastructure.web.products;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboard.DashboardConsumptionQuotaFilters;
import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.products.ProductMaster;
import com.sicom.ms.domain.usecase.products.ProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Map;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {
    private final AuthenticationGateway authenticationGateway;

    private final ProductsUseCase productsUseCase;

    @GetMapping()
    public Flux<ProductMaster> getAllProducts(Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> (String) claims.get(SICOM_AGENT))
                .flatMapMany(productsUseCase::getAllProducts);

    }
}
