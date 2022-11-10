package com.sicom.ms.infrastructure.sql.dashboardgases.vehicleyearmodel;

import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModel;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleYearModelConverter extends ObjectConverter<VehicleYearModel, VehicleYearModelData> {
}
