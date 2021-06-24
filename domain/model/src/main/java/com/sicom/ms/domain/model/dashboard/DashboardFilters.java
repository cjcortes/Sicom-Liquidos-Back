package com.sicom.ms.domain.model.dashboard;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DashboardFilters {
    String sicomAgent;
    Integer product;
    String orderType;
    long startDate;
    long endDate;
}
