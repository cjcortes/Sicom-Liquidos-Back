package com.sicom.ms.infrastructure.sql.dashboardgases.managementresponse;

import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponse;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagementResponseConverter extends ObjectConverter<ManagementResponse, ManagementResponseData> {
}
