package com.sicom.ms.domain.usecase.dashboardgases.validateequipmentexistence;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceFilters;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceGateway;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceResponse;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.validateequipmentexistence.GetValidateEquipmentExistenceFiltersRules.GET_VALIDATE_EQUIPMENT_EXISTENCE_REQUEST_RULES;

@RequiredArgsConstructor
public class GetValidateEquipmentExistenceUseCase {

    private final ObjectValidator objectValidator;

    private final ValidateEquipmentExistenceGateway validateEquipmentExistenceGateway;

    public Flux<ValidateEquipmentExistenceResponse> getByFilters(ValidateEquipmentExistenceFilters filters){
        objectValidator.validate(filters, GET_VALIDATE_EQUIPMENT_EXISTENCE_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getSerial");
        objectValidator.validate(filters, GET_VALIDATE_EQUIPMENT_EXISTENCE_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getIdTipoEquipo");
        return validateEquipmentExistenceGateway.getValidateEquipmentExistence(filters);
    }
}
