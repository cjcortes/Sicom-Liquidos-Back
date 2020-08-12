package com.sicom.ms.domain.model.consumptions;

import reactor.core.publisher.Mono;

public interface ConsumptionsGateway {
    Mono<Consumption> get(String userCode);
}
