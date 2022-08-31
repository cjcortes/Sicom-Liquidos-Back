package com.sicom.ms.infrastructure.sql.dashboardgases.totalcertifiers;

import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifier;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TotalCertifierConverter extends ObjectConverter<TotalCertifier, TotalCertifierData> {
}
