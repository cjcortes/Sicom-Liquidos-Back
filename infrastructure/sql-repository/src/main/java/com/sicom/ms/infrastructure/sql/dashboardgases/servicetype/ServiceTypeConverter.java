package com.sicom.ms.infrastructure.sql.dashboardgases.servicetype;

import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceType;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceTypeConverter extends ObjectConverter<ServiceType, ServiceTypeData> {
}
