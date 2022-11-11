package com.sicom.ms.domain.usecase.dashboardgases.equipmentstatus;

import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatus;
import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatusFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatusGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.equipmentstatus.GetEquipmentStatusByFiltersRules.GET_EQUIPMENT_STATUS_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetEquipmentStatusByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final EquipmentStatusGateway equipmentStatusGateway;

    public Flux<EquipmentStatus> getByFilters(EquipmentStatusFilters filters){
        objectValidator.validate(filters, GET_EQUIPMENT_STATUS_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getEquipmentTypeId");
        return equipmentStatusGateway.getEquipmentStatusByFilters(filters);
    }
}
