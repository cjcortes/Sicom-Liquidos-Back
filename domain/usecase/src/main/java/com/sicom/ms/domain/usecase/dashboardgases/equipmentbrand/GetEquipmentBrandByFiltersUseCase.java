package com.sicom.ms.domain.usecase.dashboardgases.equipmentbrand;

import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrand;
import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrandFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrandGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.equipmentbrand.GetEquipmentBrandByFiltersRules.GET_EQUIPMENT_BRAND_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetEquipmentBrandByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final EquipmentBrandGateway equipmentBrandGateway;

    public Flux<EquipmentBrand> getByFilters(EquipmentBrandFilters equipmentBrandFilters){
        objectValidator.validate(equipmentBrandFilters, GET_EQUIPMENT_BRAND_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getEquipmentTypeId");
        return equipmentBrandGateway.getEquipmentBrandByFilters(equipmentBrandFilters);
    }
}
