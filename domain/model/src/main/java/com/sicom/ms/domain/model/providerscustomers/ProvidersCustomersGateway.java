package com.sicom.ms.domain.model.providerscustomers;

import reactor.core.publisher.Mono;

public interface ProvidersCustomersGateway {
    Mono<ProviderCustomer> getByOrderId(String orderId);
}
