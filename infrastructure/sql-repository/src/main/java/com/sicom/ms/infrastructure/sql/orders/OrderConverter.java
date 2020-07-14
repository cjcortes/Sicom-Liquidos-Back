package com.sicom.ms.infrastructure.sql.orders;

import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderConverter extends ObjectConverter<Order, OrderData> {

}
