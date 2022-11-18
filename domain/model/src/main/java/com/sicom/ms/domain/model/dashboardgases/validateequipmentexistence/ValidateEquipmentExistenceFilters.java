package com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ValidateEquipmentExistenceFilters {
    String serial;
    String idTipoEquipo;
}
