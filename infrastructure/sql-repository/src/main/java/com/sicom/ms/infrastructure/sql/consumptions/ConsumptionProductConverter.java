package com.sicom.ms.infrastructure.sql.consumptions;

import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.model.consumptions.ConsumptionProduct;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumptionProductConverter extends ObjectConverter<ConsumptionProduct, ConsumptionProductData> {
}
