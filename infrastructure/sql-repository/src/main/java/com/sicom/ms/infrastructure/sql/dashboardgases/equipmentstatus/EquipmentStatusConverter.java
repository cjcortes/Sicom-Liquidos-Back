package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentstatus;

import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatus;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentStatusConverter extends ObjectConverter<EquipmentStatus, EquipmentStatusData> {
}
