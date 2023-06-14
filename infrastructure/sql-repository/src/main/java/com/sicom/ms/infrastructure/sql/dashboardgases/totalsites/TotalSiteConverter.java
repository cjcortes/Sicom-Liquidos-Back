package com.sicom.ms.infrastructure.sql.dashboardgases.totalsites;

import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSite;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TotalSiteConverter extends ObjectConverter<TotalSite, TotalSiteData> {
}
