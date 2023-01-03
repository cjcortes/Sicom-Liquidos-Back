package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleRequest;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleResponse;
import com.sicom.ms.domain.usecase.dashboardgases.createvehicle.CreateVehicleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class CreateVehicleController {

    private final CreateVehicleUseCase createVehicleUseCase;

    @RequestMapping(value = "/consult-create-vehicle", method = POST)
    public Mono<CreateVehicleResponse> consultCreateVehicle(@RequestBody CreateVehicleRequest request) {
        return createVehicleUseCase.create(request);
    }

    /*@PostMapping(value = "/consult-create-vehicle", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Mono<CreateVehicleResponse> consultCreateVehicle(@RequestPart("request") CreateVehicleRequest request, @RequestPart("fileData")MultipartFile fileData) {
        System.out.println("Recibido");
        return createVehicleUseCase.create(request);
    }*/
}
