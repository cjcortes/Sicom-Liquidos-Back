package com.sicom.ms.infrastructure.sql.ordertypes;

import com.sicom.ms.domain.model.ordertypes.OrderType;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderTypeConverter extends ObjectConverter<OrderType, OrderTypeData> {
}
