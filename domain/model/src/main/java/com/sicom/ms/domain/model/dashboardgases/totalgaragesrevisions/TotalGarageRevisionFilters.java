package com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TotalGarageRevisionFilters {

    String garageSicomCode;
    String startDate;
    String endDate;
}
