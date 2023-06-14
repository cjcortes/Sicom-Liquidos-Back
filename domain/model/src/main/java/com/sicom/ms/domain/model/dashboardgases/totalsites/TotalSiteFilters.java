package com.sicom.ms.domain.model.dashboardgases.totalsites;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TotalSiteFilters {

    String departmentCode;
    String cityCode;
    String conversionType;
}
