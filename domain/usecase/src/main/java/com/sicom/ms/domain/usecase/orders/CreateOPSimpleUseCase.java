package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.OPSimple;
import com.sicom.ms.domain.model.orders.OPSimpleGateway;
import com.sicom.ms.domain.model.orders.OPSimpleRequest;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class CreateOPSimpleUseCase {
    private final OPSimpleGateway opsimpleGateway;

    public Mono<OPSimple> create(OPSimpleRequest request) {
        return opsimpleGateway.createOPSimple(request);
    }
}