package com.sicom.ms.domain.model.dashboardgases.registerindividualrevision;

import com.sicom.ms.domain.model.dashboardgases.util.Process;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RegisterIndividualRevisionResponse {
    Process process;
}
