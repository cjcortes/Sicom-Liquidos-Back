package com.sicom.ms.domain.usecase.dashboardgases.validateequipmentstatus;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusFilters;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusGateway;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusResponse;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.validateequipmentstatus.GetValidateEquipmentStatusFiltersRules.GET_VALIDATE_EQUIPMENT_STATUS_REQUEST_RULES;

@RequiredArgsConstructor
public class GetValidateEquipmentStatusUseCase {

    private final ObjectValidator objectValidator;

    private final ValidateEquipmentStatusGateway validateEquipmentStatusGateway;

    public Flux<ValidateEquipmentStatusResponse> getByFilters(ValidateEquipmentStatusFilters filters){
        objectValidator.validate(filters, GET_VALIDATE_EQUIPMENT_STATUS_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getSerial");
        objectValidator.validate(filters, GET_VALIDATE_EQUIPMENT_STATUS_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getIdTipoEquipo");
        objectValidator.validate(filters, GET_VALIDATE_EQUIPMENT_STATUS_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getIdVehiculoBPM");
        return validateEquipmentStatusGateway.getValidateEquipmentStatus(filters);
    }
}
