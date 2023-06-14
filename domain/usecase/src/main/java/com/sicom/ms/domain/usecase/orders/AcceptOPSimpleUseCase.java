package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.OPSimple;
import com.sicom.ms.domain.model.orders.OPSimpleAcceptRequest;
import com.sicom.ms.domain.model.orders.OPSimpleConfirmRequest;
import com.sicom.ms.domain.model.orders.OPSimpleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AcceptOPSimpleUseCase {
    private final OPSimpleGateway opsimpleGateway;

    public Mono<OPSimple> accept(OPSimpleAcceptRequest request) {
        return opsimpleGateway.acceptOPSimple(request);
    }
}
