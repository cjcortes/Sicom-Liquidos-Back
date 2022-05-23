package com.sicom.ms.infrastructure.web.vehicles;

import com.sicom.ms.domain.model.vehicles.VehicleOpSimple;
import com.sicom.ms.domain.usecase.vehicles.GetVehiclesBySicomCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehiclesController {
    private final GetVehiclesBySicomCodeUseCase getVehiclesBySicomCodeUseCase;

    @GetMapping()
    public Flux<VehicleOpSimple> get(@RequestParam String sicomCode,
                                     @RequestParam boolean acceptOPS) {

        return getVehiclesBySicomCodeUseCase.get(sicomCode, acceptOPS);
    }
}