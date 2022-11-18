package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleResponse;
import com.sicom.ms.domain.usecase.dashboardgases.certificationvehicle.GetCertificationVehicleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class CertificationVehicleController {

    private final GetCertificationVehicleUseCase getCertificationVehicleUseCase;

    @GetMapping(value = "/certification-vehicle")
    public Flux<CertificationVehicleResponse> getCertificationVehicle(@RequestParam(defaultValue = "") String placa,
                                                                      @RequestParam(defaultValue = "") String chip,
                                                                      @RequestParam(defaultValue = "") String VIN) {

        return getCertificationVehicleUseCase.getByFilters(CertificationVehicleFilters.builder()
                        .chip(chip)
                        .vin(VIN)
                        .placa(placa).build());
    }
}
