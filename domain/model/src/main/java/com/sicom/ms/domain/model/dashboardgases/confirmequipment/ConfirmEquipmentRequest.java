package com.sicom.ms.domain.model.dashboardgases.confirmequipment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ConfirmEquipmentRequest {
    ConfirmEquipment equipo;
}
