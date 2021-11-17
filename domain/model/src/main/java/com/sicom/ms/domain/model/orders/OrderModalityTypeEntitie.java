package com.sicom.ms.domain.model.orders;


import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderModalityTypeEntitie {
    public String key;
    public String name;
    public String code;
}
