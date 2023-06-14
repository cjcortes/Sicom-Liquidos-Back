
package com.sicom.ms.infrastructure.sql.dashboard;

import com.sicom.ms.domain.model.dashboard.DashboardConsumptionQuota;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumptionQuotaConverter extends ObjectConverter<DashboardConsumptionQuota, DashboardConsumptionQuotaData> {
}

