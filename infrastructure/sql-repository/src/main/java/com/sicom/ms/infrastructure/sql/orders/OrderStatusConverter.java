package com.sicom.ms.infrastructure.sql.orders;

import com.sicom.ms.domain.model.orders.OrderStatus;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusConverter extends ObjectConverter<OrderStatus, OrderStatusData> {
}