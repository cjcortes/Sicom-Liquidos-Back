package com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus;

import reactor.core.publisher.Flux;

public interface ValidateEquipmentStatusGateway {
    Flux<ValidateEquipmentStatusResponse> getValidateEquipmentStatus(ValidateEquipmentStatusFilters request);
}
