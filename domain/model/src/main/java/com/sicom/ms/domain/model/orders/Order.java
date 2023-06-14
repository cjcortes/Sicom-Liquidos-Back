package com.sicom.ms.domain.model.orders;

import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(toBuilder = true)
public class Order {

    String authorizationCode;
    String headPlate;
    String trailer;
    String transportType;
    String driver;
    String driverIdentification;
    int transportCode;
    Date applicationDate;
    Date dispatchDate;
    String orderType;

}
