package com.sicom.ms.domain.model.plants;


import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ReceiptPlant {

    public String idPlanta;
    public String nombrePlanta;
    public String estado;

}