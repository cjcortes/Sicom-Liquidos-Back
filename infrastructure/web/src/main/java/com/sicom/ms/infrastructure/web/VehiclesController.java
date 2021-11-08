package com.sicom.ms.infrastructure.web;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.model.vehicles.Vehicles;
import com.sicom.ms.domain.usecase.vehicles.GetVehiclesBySicomAgentIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehiclesController {
    private final GetVehiclesBySicomAgentIdUseCase getVehiclesBySicomAgentIdUseCase;

    @GetMapping(value = "/get-vehicles")
    public Flux<Vehicles> get() {
        return getVehiclesBySicomAgentIdUseCase.getVehiclesBySicomAgentId("650001");
    }
}