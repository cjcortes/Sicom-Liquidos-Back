
package com.sicom.ms.domain.model.products;

import reactor.core.publisher.Flux;

public interface ProductsMasterGateway {

    Flux<ProductMaster> getAllProducts(String sicomAgent);

}
