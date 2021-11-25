package com.sicom.ms.domain.model.products;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProductOPSimple {

    public int key;
    public String productName;
    public String productType;
    public String productCode;
    public double storedCapacity;
    public int numberDispensers;
    public int numberHoses;
    public int tankNumber;
    public double productQuota;
    public int nominalTotalCapacity;
    public int totalOperatingCapacity;
    public int sicomAgentCode;
}
