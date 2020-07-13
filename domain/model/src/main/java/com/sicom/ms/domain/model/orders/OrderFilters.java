package com.sicom.ms.domain.model.orders;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class OrderFilters {

    String authCode;
    String clientCode;
    String providerPlantCode;
    String orderType;
    Date suggestedDeliveryStartDate;
    Date suggestedDeliveryEndDate;

}
