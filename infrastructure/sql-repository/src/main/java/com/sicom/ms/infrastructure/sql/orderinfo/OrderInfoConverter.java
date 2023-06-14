package com.sicom.ms.infrastructure.sql.orderinfo;

import com.sicom.ms.domain.model.orderinfo.OrderInfo;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderInfoConverter extends ObjectConverter<OrderInfo, OrderInfoData> {

}
