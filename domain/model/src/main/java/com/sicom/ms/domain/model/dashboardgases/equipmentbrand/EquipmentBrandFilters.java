package com.sicom.ms.domain.model.dashboardgases.equipmentbrand;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EquipmentBrandFilters {
    String equipmentBrandId;
    String equipmentTypeId;
}
