package com.sicom.ms.domain.usecase.products;

import com.sicom.ms.domain.model.products.ProductOPSimple;
import com.sicom.ms.domain.model.products.ProductsOPSimpleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProductsOPSimpleBySicomCodeUseCase {
    private final ProductsOPSimpleGateway productsOPSimpleGateway;

    public Flux<ProductOPSimple> get(String codigoSicomSol,
                                     String codigoSicomProv,
                                     String idPlantaRecibo,
                                     String idPlantaAbastecimiento) {
        return productsOPSimpleGateway.getProductsOPSimpleBySicomCodeUseCase(codigoSicomSol,
                codigoSicomProv, idPlantaRecibo, idPlantaAbastecimiento);
    }
}
