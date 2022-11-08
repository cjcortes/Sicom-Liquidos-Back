package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentRequest;
import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentResponse;
import com.sicom.ms.domain.usecase.dashboardgases.equipment.CreateEquipmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private final CreateEquipmentUseCase createEquipmentUseCase;

    @PostMapping(value = "/create-equipment")
    public Mono<EquipmentResponse> createOpSimple(@RequestBody EquipmentRequest request) {
        return createEquipmentUseCase.create(request);
    }
}
