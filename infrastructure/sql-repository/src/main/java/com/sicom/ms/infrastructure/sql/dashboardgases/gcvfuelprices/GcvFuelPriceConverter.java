package com.sicom.ms.infrastructure.sql.dashboardgases.gcvfuelprices;

import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPrice;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GcvFuelPriceConverter extends ObjectConverter<GcvFuelPrice, GcvFuelPriceData> {
}
