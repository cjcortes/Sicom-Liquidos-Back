package com.sicom.ms.domain.model.dashboardgases.equipmentmodel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EquipmentModelFilters {
    String equipmentBrandId;
    String equipmentModelId;
}
