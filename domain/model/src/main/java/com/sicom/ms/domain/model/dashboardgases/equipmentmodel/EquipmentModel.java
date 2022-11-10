package com.sicom.ms.domain.model.dashboardgases.equipmentmodel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EquipmentModel {
    int equipmentModelId;
    String equipmentModelName;
    int equipmentModelCode;
    int equipmentBrandId;
}
