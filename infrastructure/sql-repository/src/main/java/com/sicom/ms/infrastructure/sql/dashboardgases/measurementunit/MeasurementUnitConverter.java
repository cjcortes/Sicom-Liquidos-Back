package com.sicom.ms.infrastructure.sql.dashboardgases.measurementunit;

import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnit;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementUnitConverter extends ObjectConverter<MeasurementUnit, MeasurementUnitData> {
}
