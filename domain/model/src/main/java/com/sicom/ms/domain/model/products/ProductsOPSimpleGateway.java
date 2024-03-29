package com.sicom.ms.domain.model.products;

import reactor.core.publisher.Flux;

public interface ProductsOPSimpleGateway {
    Flux<ProductOPSimple> getProductsOPSimpleBySicomCodeUseCase(String codigoSicomSol,
                                                                String codigoSicomProv,
                                                                String idPlantaRecibo,
                                                                String idPlantaAbastecimiento);

}
