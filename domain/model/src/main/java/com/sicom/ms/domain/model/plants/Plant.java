package com.sicom.ms.domain.model.plants;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Plant {

    public int idPlant;
    public String plantName;
    public String status;
    public int idAgent;
    public double nominalTotalCapacity;
    public double totalOperatingCapacity;

}