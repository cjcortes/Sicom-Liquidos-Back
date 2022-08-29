package com.sicom.ms.domain.model.dashboardgases.totalsites;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TotalSite {

    String name;
    String value;
    int numericValue;
}
