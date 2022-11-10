package com.sicom.ms.domain.usecase.dashboardgases.equipmentmodel;

import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModelFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEquipmentModelByFiltersRules {
    public static final Rules<EquipmentModelFilters> GET_EQUIPMENT_MODEL_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(EquipmentModelFilters::getEquipmentBrandId, "equipmentModelFilters", "equipmentBrandId"),
            Rule.of("equipmentModelFilters.equipmentBrandIdCanNotBeEmpty",
                    "you must send equipment brand id",
                    object -> (!object.getEquipmentBrandId().equals("-1") || object.getEquipmentBrandId().equals(null)))
    );
}
