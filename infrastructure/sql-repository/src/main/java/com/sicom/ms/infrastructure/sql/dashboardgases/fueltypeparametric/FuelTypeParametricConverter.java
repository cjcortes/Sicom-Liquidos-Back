package com.sicom.ms.infrastructure.sql.dashboardgases.fueltypeparametric;

import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametric;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FuelTypeParametricConverter extends ObjectConverter<FuelTypeParametric, FuelTypeParametricData> {
}
