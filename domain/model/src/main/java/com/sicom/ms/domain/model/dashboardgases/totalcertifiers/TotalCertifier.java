package com.sicom.ms.domain.model.dashboardgases.totalcertifiers;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TotalCertifier {

    String name;
    String value;
    int numericValue;
}
