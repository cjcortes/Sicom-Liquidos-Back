package com.sicom.ms.domain.model.providers;

import com.sicom.ms.domain.model.plants.Plant;
import reactor.core.publisher.Flux;

public interface ProvidersGateway {
    Flux<Provider> getProvidersBySicomCode(String sicomCode);
}
