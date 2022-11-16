package com.sicom.ms.infrastructure.sql.dashboardgases.equipmenttype;

import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentType;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentTypeConverter extends ObjectConverter<EquipmentType, EquipmentTypeData> {
}
