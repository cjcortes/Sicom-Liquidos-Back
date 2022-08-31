package com.sicom.ms.domain.model.dashboardgases.participationgarages;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ParticipationGarage {

    String name;
    String value;
    int numericValue;
}
