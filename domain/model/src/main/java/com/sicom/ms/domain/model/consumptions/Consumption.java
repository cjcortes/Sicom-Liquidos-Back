package com.sicom.ms.domain.model.consumptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Consumption {
    long assignedQuota;
    long finalQuota;
    long availableQuota;
    long consumption;
    long cededVolume;
    double percent;
}
