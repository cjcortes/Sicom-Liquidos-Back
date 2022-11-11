package com.sicom.ms.domain.model.dashboardgases.equipmentstatus;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EquipmentStatusFilters {
    String equipmentStatusId;
    String equipmentTypeId;
}
