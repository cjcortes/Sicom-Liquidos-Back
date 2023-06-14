package com.sicom.ms.infrastructure.sql.dashboardgases.conversiongarage;

import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarage;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConversionGarageConverter extends ObjectConverter<ConversionGarage, ConversionGarageData> {
}
