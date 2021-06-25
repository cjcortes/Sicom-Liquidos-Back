package com.sicom.ms.domain.model.consumptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ConsumptionProduct {

    String productName;
    String value;

}
