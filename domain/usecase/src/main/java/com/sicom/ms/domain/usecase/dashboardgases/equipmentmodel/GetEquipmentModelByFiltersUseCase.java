package com.sicom.ms.domain.usecase.dashboardgases.equipmentmodel;

import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModel;
import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModelFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModelGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.equipmentmodel.GetEquipmentModelByFiltersRules.GET_EQUIPMENT_MODEL_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetEquipmentModelByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final EquipmentModelGateway equipmentModelGateway;

    public Flux<EquipmentModel> getByFilters(EquipmentModelFilters filters){
        objectValidator.validate(filters, GET_EQUIPMENT_MODEL_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getEquipmentBrandId");
        return equipmentModelGateway.getEquipmentModelsByFilters(filters);
    }
}
