package com.sicom.ms.domain.model.plants;


import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Plant{

    public int idPlanta;
    public String nombrePlanta;
    public String estado;
    public int idAgente;

}