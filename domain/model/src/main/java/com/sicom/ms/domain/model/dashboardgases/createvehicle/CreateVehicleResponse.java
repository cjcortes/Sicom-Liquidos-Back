package com.sicom.ms.domain.model.dashboardgases.createvehicle;

import com.sicom.ms.domain.model.dashboardgases.util.Process;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CreateVehicleResponse {
    public Process process;
}
