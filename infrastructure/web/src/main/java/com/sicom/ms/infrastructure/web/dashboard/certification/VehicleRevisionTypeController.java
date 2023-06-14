package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeResponse;
import com.sicom.ms.domain.usecase.dashboardgases.vehiclerevisiontype.GetVehicleRevisionTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class VehicleRevisionTypeController {

    private final GetVehicleRevisionTypeUseCase getVehicleRevisionTypeUseCase;

    @GetMapping(value = "/vehicle-revision-type")
    public Flux<VehicleRevisionTypeResponse> getVehicleRevisionType(@RequestParam(defaultValue = "-1") String placa,
                                                                    @RequestParam(defaultValue = "-1") String chip,
                                                                    @RequestParam(defaultValue = "-1") String VIN) {

        return getVehicleRevisionTypeUseCase.getByFilters(VehicleRevisionTypeFilters.builder()
                .chip(chip)
                .vin(VIN)
                .placa(placa).build());
    }
}
