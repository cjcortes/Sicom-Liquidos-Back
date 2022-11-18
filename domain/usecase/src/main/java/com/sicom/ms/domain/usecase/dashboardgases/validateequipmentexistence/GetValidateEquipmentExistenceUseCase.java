package com.sicom.ms.domain.usecase.dashboardgases.validateequipmentexistence;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceFilters;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceGateway;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetValidateEquipmentExistenceUseCase {

    private final ValidateEquipmentExistenceGateway validateEquipmentExistenceGateway;

    public Flux<ValidateEquipmentExistenceResponse> getByFilters(ValidateEquipmentExistenceFilters filters){
        return validateEquipmentExistenceGateway.getValidateEquipmentExistence(filters);
    }
}
