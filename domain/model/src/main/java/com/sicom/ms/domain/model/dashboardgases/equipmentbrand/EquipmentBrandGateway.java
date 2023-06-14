package com.sicom.ms.domain.model.dashboardgases.equipmentbrand;

import reactor.core.publisher.Flux;

public interface EquipmentBrandGateway {
    Flux<EquipmentBrand> getEquipmentBrandByFilters(EquipmentBrandFilters request);
}
