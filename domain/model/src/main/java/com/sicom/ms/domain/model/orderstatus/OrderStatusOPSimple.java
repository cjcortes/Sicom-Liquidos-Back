package com.sicom.ms.domain.model.orderstatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderStatusOPSimple{
    @JsonProperty("@key")
    public String key;
    public String sCodigo;
    public String sCodigoWS;
    public String sNombre;
}

