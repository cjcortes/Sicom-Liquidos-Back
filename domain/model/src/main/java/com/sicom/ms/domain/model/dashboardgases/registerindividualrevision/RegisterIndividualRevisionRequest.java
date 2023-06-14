package com.sicom.ms.domain.model.dashboardgases.registerindividualrevision;

import com.sicom.ms.domain.model.dashboardgases.createvehicle.InformacionCaso;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class RegisterIndividualRevisionRequest {
    InformacionCaso informacionCaso;
    IndividualRevision revisionIndividual;
}
