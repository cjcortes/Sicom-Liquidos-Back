package com.sicom.ms.domain.usecase.dashboardgases.equipmentbrand;

import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrandFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEquipmentBrandByFiltersRules {
    public static final Rules<EquipmentBrandFilters> GET_EQUIPMENT_BRAND_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(EquipmentBrandFilters::getEquipmentTypeId, "equipmentBrandFilters", "equipmentTypeId"),
            Rule.of("equipmentBrandFilters.equipmentTypeIdCanNotBeEmpty",
                    "you must send equipment type id",
                    object -> (!object.getEquipmentTypeId().equals("-1") || object.getEquipmentTypeId().equals(null)))
    );
}
