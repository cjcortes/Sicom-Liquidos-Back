package com.sicom.ms.domain.usecase.dashboardgases.certificationvehicle;

import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleGateway;
import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetCertificationVehicleUseCase {

    private final CertificationVehicleGateway certificationVehicleGateway;

    public Flux<CertificationVehicleResponse> getByFilters(CertificationVehicleFilters filters){
        return certificationVehicleGateway.getCertificationVehicle(filters);
    }
}
