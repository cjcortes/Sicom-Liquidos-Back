package com.sicom.ms.infrastructure.sql.dashboardgases.totalgaragerevisions;

import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevision;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TotalGarageRevisionConverter extends ObjectConverter<TotalGarageRevision, TotalGarageRevisionData> {
}
