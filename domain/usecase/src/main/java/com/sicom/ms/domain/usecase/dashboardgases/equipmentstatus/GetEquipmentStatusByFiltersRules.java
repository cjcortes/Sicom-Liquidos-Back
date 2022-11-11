package com.sicom.ms.domain.usecase.dashboardgases.equipmentstatus;

import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModelFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatusFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetEquipmentStatusByFiltersRules {
    public static final Rules<EquipmentStatusFilters> GET_EQUIPMENT_STATUS_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(EquipmentStatusFilters::getEquipmentTypeId, "equipmentStatusFilters", "equipmentTypeId"),
            Rule.of("equipmentStatusFilters.equipmentTypeIdCanNotBeEmpty",
                    "you must send equipment type id",
                    object -> (!object.getEquipmentTypeId().equals("-1") || object.getEquipmentTypeId().equals(null)))
    );
}
