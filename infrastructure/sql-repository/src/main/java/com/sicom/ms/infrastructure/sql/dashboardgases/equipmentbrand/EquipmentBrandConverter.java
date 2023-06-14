package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentbrand;

import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrand;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentBrandConverter extends ObjectConverter<EquipmentBrand, EquipmentBrandData> {
}
