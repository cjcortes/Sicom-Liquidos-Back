package com.sicom.ms.domain.model.products;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Product {

    String name;
    String state;
    double requestedAmount;
    double acceptedAmount;
    int dispatchedAmount;

}
