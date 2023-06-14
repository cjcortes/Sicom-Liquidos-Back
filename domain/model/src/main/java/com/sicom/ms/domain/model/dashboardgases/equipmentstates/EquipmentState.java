package com.sicom.ms.domain.model.dashboardgases.equipmentstates;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EquipmentState {

    String code;
    String description;
}
