package com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class IndividualRevisionInformationFilters {
    String placa;
    String vin;
    String chip;
    String numeroCaso;
}
