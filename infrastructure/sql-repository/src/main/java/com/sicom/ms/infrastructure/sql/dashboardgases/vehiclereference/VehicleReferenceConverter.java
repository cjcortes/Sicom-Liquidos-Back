package com.sicom.ms.infrastructure.sql.dashboardgases.vehiclereference;

import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReference;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleReferenceConverter extends ObjectConverter<VehicleReference, VehicleReferenceData> {
}
