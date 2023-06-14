package com.sicom.ms.domain.model.dashboardgases.equipmentstates;

import reactor.core.publisher.Flux;

public interface EquipmentStatesGateway {
    Flux<EquipmentState> getEquipmentState();
}
