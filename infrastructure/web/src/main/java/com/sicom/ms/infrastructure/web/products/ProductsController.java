package com.sicom.ms.infrastructure.web.products;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboard.DashboardConsumptionQuotaFilters;
import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.products.ProductMaster;
import com.sicom.ms.domain.model.products.ProductOPSimple;
import com.sicom.ms.domain.usecase.products.GetProductsOPSimpleBySicomCodeUseCase;
import com.sicom.ms.domain.usecase.products.ProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    private final GetProductsOPSimpleBySicomCodeUseCase productsOPSimpleBySicomCodeUseCase;

    @GetMapping()
    public Flux<ProductMaster> getAllProducts(Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> (String) claims.get(SICOM_AGENT))
                .flatMapMany(productsUseCase::getAllProducts);

    }

    @GetMapping(value = "/op-simple")
    public Flux<ProductOPSimple> getAllProductsOPSimple(@RequestParam String codigoSicomSol,
                                                        @RequestParam String codigoSicomProv,
                                                        @RequestParam String idPlantaRecibo,
                                                        @RequestParam String idPlantaAbastecimiento) {

        return productsOPSimpleBySicomCodeUseCase.get(codigoSicomSol,
                codigoSicomProv, idPlantaRecibo, idPlantaAbastecimiento);
    }
}
