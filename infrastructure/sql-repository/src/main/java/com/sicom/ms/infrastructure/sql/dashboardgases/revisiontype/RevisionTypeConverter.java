package com.sicom.ms.infrastructure.sql.dashboardgases.revisiontype;

import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionType;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RevisionTypeConverter extends ObjectConverter<RevisionType, RevisionTypeData> {
}
