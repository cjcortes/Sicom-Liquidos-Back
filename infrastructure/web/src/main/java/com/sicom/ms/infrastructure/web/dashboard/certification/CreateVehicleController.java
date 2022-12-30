package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleRequest;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleResponse;
import com.sicom.ms.domain.usecase.dashboardgases.createvehicle.CreateVehicleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class CreateVehicleController {

    private final CreateVehicleUseCase createVehicleUseCase;

    //@PostMapping(value = "/consult-create-vehicle")
    @RequestMapping(path = "/consult-create-vehicle", method = POST, consumes = { MediaType.ALL_VALUE})
    public Mono<CreateVehicleResponse> consultCreateVehicle(@RequestBody CreateVehicleRequest request) {
        return createVehicleUseCase.create(request);
    }
}
