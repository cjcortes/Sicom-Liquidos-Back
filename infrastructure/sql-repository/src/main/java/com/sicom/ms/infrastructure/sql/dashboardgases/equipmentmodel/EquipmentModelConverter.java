package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentmodel;

import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModel;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentModelConverter extends ObjectConverter<EquipmentModel, EquipmentModelData> {
}
