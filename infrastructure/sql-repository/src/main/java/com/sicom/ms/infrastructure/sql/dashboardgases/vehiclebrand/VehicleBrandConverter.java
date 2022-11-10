package com.sicom.ms.infrastructure.sql.dashboardgases.vehiclebrand;

import com.sicom.ms.domain.model.dashboardgases.vehiclebrand.VehicleBrand;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleBrandConverter extends ObjectConverter<VehicleBrand, VehicleBrandData> {
}
