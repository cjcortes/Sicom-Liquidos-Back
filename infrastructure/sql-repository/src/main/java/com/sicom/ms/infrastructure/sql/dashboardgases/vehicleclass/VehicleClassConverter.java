package com.sicom.ms.infrastructure.sql.dashboardgases.vehicleclass;

import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClass;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleClassConverter extends ObjectConverter<VehicleClass, VehicleClassData> {
}
