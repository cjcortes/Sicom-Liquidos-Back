package com.sicom.ms.domain.model.dashboard;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class DashboardTotal {

    String name;
    String value;

}
