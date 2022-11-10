package com.sicom.ms.domain.model.dashboardgases.equipmentmodel;

import reactor.core.publisher.Flux;

public interface EquipmentModelGateway {
    Flux<EquipmentModel> getEquipmentModelsByFilters(EquipmentModelFilters request);
}
