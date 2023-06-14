package com.sicom.ms.domain.model.dashboardgases.startgcvcertification;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class StartGCVCertificationRequest {
    public String domain;
    public String username;
    public String process;
}
