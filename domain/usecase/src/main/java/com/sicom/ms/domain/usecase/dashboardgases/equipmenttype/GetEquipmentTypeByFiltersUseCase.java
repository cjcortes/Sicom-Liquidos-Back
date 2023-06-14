package com.sicom.ms.domain.usecase.dashboardgases.equipmenttype;

import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentType;
import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentTypeGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetEquipmentTypeByFiltersUseCase {

    private final EquipmentTypeGateway equipmentTypeGateway;

    public Flux<EquipmentType> getByFilters(EquipmentTypeFilters filters){
        return equipmentTypeGateway.getEquipmentTypeByFilters(filters);
    }
}
