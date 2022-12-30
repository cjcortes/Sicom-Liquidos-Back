package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleRequest;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleResponse;
import com.sicom.ms.domain.usecase.dashboardgases.createvehicle.CreateVehicleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class CreateVehicleController {

    private final CreateVehicleUseCase createVehicleUseCase;

    @PostMapping(value = "/consult-create-vehicle")
    public Mono<CreateVehicleResponse> consultCreateVehicle(@RequestPart CreateVehicleRequest request) {
        return createVehicleUseCase.create(request);
    }
}
