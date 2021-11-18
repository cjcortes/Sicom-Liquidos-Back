package com.sicom.ms.domain.usecase.providers;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.model.plants.PlantsGateway;
import com.sicom.ms.domain.model.providers.Provider;
import com.sicom.ms.domain.model.providers.ProvidersGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProvidersBySicomCodeUseCase {
    private final ProvidersGateway providersGateway;

    public Flux<Provider> get(String sicomCode) {
        return providersGateway.getProvidersBySicomCode(sicomCode);
    }
}
