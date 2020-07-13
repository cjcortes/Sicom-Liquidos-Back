package com.sicom.ms.domain.model.ordertypes;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderType {

    int code;
    String name;
    String description;

}
