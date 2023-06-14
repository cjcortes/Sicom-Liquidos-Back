package com.sicom.ms.domain.model.consumptions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ConsumptionsProductsFilters {

    String sicomAgent;
    Integer product;
    String orderType;
    long startDate;
    long endDate;

}
