package com.sicom.ms.domain.model.dashboardgases.confirmequipment;

import reactor.core.publisher.Mono;

public interface ConfirmEquipmentGateway {
    Mono<ConfirmEquipmentResponse> createConfirmEquipment(ConfirmEquipmentRequest request);
}
