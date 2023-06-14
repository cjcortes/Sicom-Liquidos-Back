package com.sicom.ms.infrastructure.sql.dashboardgases.authorizevehicles;

import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicle;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorizeVehicleConverter extends ObjectConverter<AuthorizeVehicle, AuthorizeVehicleData> {
}
