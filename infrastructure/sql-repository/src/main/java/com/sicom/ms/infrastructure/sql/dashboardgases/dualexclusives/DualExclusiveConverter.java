package com.sicom.ms.infrastructure.sql.dashboardgases.dualexclusives;

import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusive;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DualExclusiveConverter extends ObjectConverter<DualExclusive, DualExclusiveData> {
}
