package com.sicom.ms.domain.model.dashboardgases.equipmenttype;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EquipmentTypeFilters {
    String equipmentTypeId;
}
