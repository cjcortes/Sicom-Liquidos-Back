
package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class ConfirmOPSimpleUseCase {
    private final OPSimpleGateway opsimpleGateway;

    public Mono<OPSimple> confirm(OPSimpleConfirmRequest request) {
        return opsimpleGateway.confirmOPSimple(request);
    }
}