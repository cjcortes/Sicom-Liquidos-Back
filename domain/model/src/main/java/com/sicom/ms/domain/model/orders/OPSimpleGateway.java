package com.sicom.ms.domain.model.orders;

import reactor.core.publisher.Mono;

public interface OPSimpleGateway {
    Mono<OPSimple> createOPSimple(OPSimpleRequest request);
    Mono<OPSimplePerform> confirmOPSimple(OPSimpleConfirmRequest request);
}
