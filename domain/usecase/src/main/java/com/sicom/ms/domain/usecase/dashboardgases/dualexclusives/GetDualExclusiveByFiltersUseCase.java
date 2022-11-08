package com.sicom.ms.domain.usecase.dashboardgases.dualexclusives;

import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusive;
import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusiveFilters;
import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusiveGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetDualExclusiveByFiltersUseCase {

    private final DualExclusiveGateway dualExclusiveGateway;

    public Flux<DualExclusive> getByFilters(DualExclusiveFilters filters){
        return dualExclusiveGateway.getDualExlcusivesByFilters(filters);
    }
}
