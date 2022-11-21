package com.sicom.ms.domain.usecase.dashboardgases.validateequipmentexistence;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetValidateEquipmentExistenceFiltersRules {

    public static final Rules<ValidateEquipmentExistenceFilters> GET_VALIDATE_EQUIPMENT_EXISTENCE_REQUEST_RULES = Rules.of(
            cannotBeEmpty(ValidateEquipmentExistenceFilters::getSerial, "validateEquipmentStatusFilters", "serial"),
            Rule.of("validateEquipmentStatusFilters.serialCanNotBeEmpty",
                    "you must send equipment serial",
                    object -> (!object.getSerial().equals("-1") || object.getSerial().equals(null))),
            cannotBeEmpty(ValidateEquipmentExistenceFilters::getIdTipoEquipo, "validateEquipmentStatusFilters", "idTipoEquipo"),
            Rule.of("validateEquipmentStatusFilters.idTipoEquipoCanNotBeEmpty",
                    "you must send idTipoEquipo",
                    object -> (!object.getIdTipoEquipo().equals("-1") || object.getIdTipoEquipo().equals(null)))
    );
}
