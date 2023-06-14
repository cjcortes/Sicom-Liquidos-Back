package com.sicom.ms.domain.model.dashboardgases.equipmenttype;

import reactor.core.publisher.Flux;

public interface EquipmentTypeGateway {
    Flux<EquipmentType> getEquipmentTypeByFilters(EquipmentTypeFilters request);
}
