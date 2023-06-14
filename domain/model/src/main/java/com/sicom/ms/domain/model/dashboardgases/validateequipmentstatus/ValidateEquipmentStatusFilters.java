package com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ValidateEquipmentStatusFilters {
    String serial;
    String idTipoEquipo;
    String idVehiculoBPM;
}
