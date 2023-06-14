package com.sicom.ms.domain.model.dashboardgases.cylindertype;

import reactor.core.publisher.Flux;

public interface CylinderTypeGateway {
    Flux<CylinderType> getCylinderTypeByFilters(CylinderTypeFilters request);
}
