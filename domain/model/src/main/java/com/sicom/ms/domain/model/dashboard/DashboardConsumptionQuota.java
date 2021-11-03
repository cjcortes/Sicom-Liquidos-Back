
package com.sicom.ms.domain.model.dashboard;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DashboardConsumptionQuota {

    String consumption;
    String quota;
    String consumptionStr;

}
