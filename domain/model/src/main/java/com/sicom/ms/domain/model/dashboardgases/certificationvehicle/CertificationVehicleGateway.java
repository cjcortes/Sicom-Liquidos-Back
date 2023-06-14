package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import reactor.core.publisher.Flux;

public interface CertificationVehicleGateway {
    Flux<CertificationVehicleResponse> getCertificationVehicle(CertificationVehicleFilters filters);
}
