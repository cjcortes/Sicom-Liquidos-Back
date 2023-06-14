package com.sicom.ms.domain.model.orders;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderStatusFilters {

    String sicomAgent;
    Integer product;
    String orderType;
    long startDate;
    long endDate;

}