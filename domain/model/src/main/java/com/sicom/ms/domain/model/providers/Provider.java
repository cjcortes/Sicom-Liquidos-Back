package com.sicom.ms.domain.model.providers;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Provider {
    public int applicantId;
    public int applicantSicomCode;
    public int providerId;
    public int providerSicomCode;
    public String startContractDate;
    public String endContractDate;
}
