package com.sicom.ms.domain.model.dashboardgases.registerindividualrevision;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class IndividualRevision {
    GarageRevision revisionTaller;
    VehicleRevision vehiculoRevision;
}
