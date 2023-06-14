package com.sicom.ms.infrastructure.sql.dashboardgases.revisiongarage;

import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarage;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RevisionGarageConverter extends ObjectConverter<RevisionGarage, RevisionGarageData> {
}
