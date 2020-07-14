package com.sicom.ms.domain.model.orders;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderFilters {

    String sicomAgent;
    String authCode;
    String clientCode;
    String providerPlantCode;
    String orderType;
    long suggestedDeliveryStartDate;
    long suggestedDeliveryEndDate;

}
