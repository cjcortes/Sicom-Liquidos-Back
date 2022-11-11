package com.sicom.ms.domain.model.dashboardgases.equipmentstatus;

import reactor.core.publisher.Flux;

public interface EquipmentStatusGateway {
    Flux<EquipmentStatus> getEquipmentStatusByFilters(EquipmentStatusFilters request);
}
