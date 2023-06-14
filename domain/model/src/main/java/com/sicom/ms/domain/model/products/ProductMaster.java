package com.sicom.ms.domain.model.products;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProductMaster {

    int productCode;
    String name;

}
