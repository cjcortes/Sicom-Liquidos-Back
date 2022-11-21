package com.sicom.ms.domain.usecase.dashboardgases.validateequipmentstatus;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetValidateEquipmentStatusFiltersRules {
    public static final Rules<ValidateEquipmentStatusFilters> GET_VALIDATE_EQUIPMENT_STATUS_REQUEST_RULES = Rules.of(
            cannotBeEmpty(ValidateEquipmentStatusFilters::getSerial, "validateEquipmentStatusFilters", "serial"),
            Rule.of("validateEquipmentStatusFilters.serialCanNotBeEmpty",
                    "you must send equipment serial",
                    object -> (!object.getSerial().equals("-1") || object.getSerial().equals(null))),
            cannotBeEmpty(ValidateEquipmentStatusFilters::getIdTipoEquipo, "validateEquipmentStatusFilters", "idTipoEquipo"),
            Rule.of("validateEquipmentStatusFilters.idTipoEquipoCanNotBeEmpty",
                    "you must send idTipoEquipo",
                    object -> (!object.getIdTipoEquipo().equals("-1") || object.getIdTipoEquipo().equals(null))),
            cannotBeEmpty(ValidateEquipmentStatusFilters::getIdVehiculoBPM, "validateEquipmentStatusFilters", "idVehiculoBPM"),
            Rule.of("validateEquipmentStatusFilters.idVehiculoBPMCanNotBeEmpty",
                    "you must send idVehiculoBPM",
                    object -> (!object.getIdVehiculoBPM().equals("-1") || object.getIdVehiculoBPM().equals(null)))
    );
}
