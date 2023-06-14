package com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DedicateConvertVehicle {
    int dedicateConvertId;
    String dedicateConvertName;
    String dedicateConvertCode;
}
