package com.sicom.ms.domain.model.dashboardgases.startgcvcertification;

import com.sicom.ms.domain.model.dashboardgases.util.Process;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class StartGCVCertificationResponse {
    public Process process;
}
