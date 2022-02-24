package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.OPSQuota;
import com.sicom.ms.domain.model.orders.OPSimpleGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetOPSQuotaUseCase {
    private final OPSimpleGateway opsimpleGateway;

    public Flux<OPSQuota> get(String opsCaseNumber, String sicomCode) {
        return opsimpleGateway.getOPSQuota(opsCaseNumber, sicomCode);
    }
}
