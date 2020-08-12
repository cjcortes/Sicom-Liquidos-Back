package com.sicom.ms.domain.model.consumptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Consumption {
    int year;
    int month;
    long assignedQuota;
    long availableQuota;
    long consumption;
    double percent;
}
