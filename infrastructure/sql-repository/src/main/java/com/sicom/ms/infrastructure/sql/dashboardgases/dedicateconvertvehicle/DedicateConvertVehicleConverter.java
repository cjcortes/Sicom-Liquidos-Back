package com.sicom.ms.infrastructure.sql.dashboardgases.dedicateconvertvehicle;

import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicle;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DedicateConvertVehicleConverter extends ObjectConverter<DedicateConvertVehicle, DedicateConvertVehicleData> {
}
