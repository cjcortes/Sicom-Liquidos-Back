package com.sicom.ms.infrastructure.sql.dashboardgases.totalconvertvehicles;

import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicle;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TotalConvertVehicleConverter extends ObjectConverter<TotalConvertVehicle, TotalConvertVehicleData> {
}
