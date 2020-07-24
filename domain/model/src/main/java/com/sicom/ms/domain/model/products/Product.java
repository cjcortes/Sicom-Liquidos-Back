package com.sicom.ms.domain.model.products;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Product {

    String name;
    String state;
    int requestedAmount;
    int acceptedAmount;
    int dispatchedAmount;

}
