package com.sicom.ms.domain.model.orders;

import com.sicom.ms.domain.model.orderinfo.OrderInfo;
import com.sicom.ms.domain.model.providerscustomers.ProviderCustomer;
import com.sicom.ms.domain.model.vehicles.Vehicle;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderDetail {

    OrderInfo orderInfo;
    ProviderCustomer providerCustomer;
    Vehicle vehicle;

}
