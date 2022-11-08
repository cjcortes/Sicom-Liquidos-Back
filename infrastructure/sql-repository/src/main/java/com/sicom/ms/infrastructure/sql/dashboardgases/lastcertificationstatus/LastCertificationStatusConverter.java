package com.sicom.ms.infrastructure.sql.dashboardgases.lastcertificationstatus;

import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatus;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LastCertificationStatusConverter extends ObjectConverter<LastCertificationStatus, LastCertificationStatusData> {
}
