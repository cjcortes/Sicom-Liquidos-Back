package com.sicom.ms.infrastructure.sql.dashboardgases.cylindertype;

import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderType;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CylinderTypeConverter extends ObjectConverter<CylinderType, CylinderTypeData> {
}
