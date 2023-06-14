package com.sicom.ms.infrastructure.sql.consumptions;

import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumptionConverter extends ObjectConverter<Consumption, ConsumptionData> {
}
