package com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence;

import reactor.core.publisher.Flux;

public interface ValidateEquipmentExistenceGateway {
    Flux<ValidateEquipmentExistenceResponse> getValidateEquipmentExistence(ValidateEquipmentExistenceFilters filters);
}
