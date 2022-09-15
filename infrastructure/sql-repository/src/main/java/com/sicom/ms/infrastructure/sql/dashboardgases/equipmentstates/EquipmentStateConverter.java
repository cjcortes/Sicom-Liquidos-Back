package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentstates;

import com.sicom.ms.domain.model.dashboardgases.equipmentstates.EquipmentState;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentStateConverter extends ObjectConverter<EquipmentState, EquipmentStateData> {
}
