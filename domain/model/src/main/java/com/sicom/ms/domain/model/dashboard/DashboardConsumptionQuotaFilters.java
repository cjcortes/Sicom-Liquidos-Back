
package com.sicom.ms.domain.model.dashboard;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DashboardConsumptionQuotaFilters {

    String sicomAgent;
    Integer product;
    String orderType;
    Integer year;
    Integer month;

}
