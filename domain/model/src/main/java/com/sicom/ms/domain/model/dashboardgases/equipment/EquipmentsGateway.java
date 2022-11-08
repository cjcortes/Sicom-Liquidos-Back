package com.sicom.ms.domain.model.dashboardgases.equipment;

import reactor.core.publisher.Mono;

public interface EquipmentsGateway {
    Mono<EquipmentResponse> createEquipment(EquipmentRequest request);
}
