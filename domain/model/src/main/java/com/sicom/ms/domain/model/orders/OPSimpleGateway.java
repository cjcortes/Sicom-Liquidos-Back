package com.sicom.ms.domain.model.orders;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OPSimpleGateway {
    Mono<OPSimple> createOPSimple(OPSimpleRequest request);
    Mono<OPSimple> confirmOPSimple(OPSimpleConfirmRequest request);
    Flux<OPSQuota> getOPSQuota(String opsCaseNumber, String sicomCode);
}
