package com.sicom.ms.domain.model.dashboardgases.dualexclusives;

import reactor.core.publisher.Flux;

public interface DualExclusiveGateway {
    Flux<DualExclusive> getDualExlcusivesByFilters(DualExclusiveFilters request);
}
