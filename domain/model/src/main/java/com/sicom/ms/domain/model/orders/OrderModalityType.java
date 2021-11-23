package com.sicom.ms.domain.model.orders;


import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderModalityType {
    public String code;
    public String name;
}
