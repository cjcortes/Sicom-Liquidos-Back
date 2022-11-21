package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentRequest;
import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentResponse;
import com.sicom.ms.domain.usecase.dashboardgases.confirmequipment.CreateConfirmEquipmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class ConfirmEquipmentController {

    private final CreateConfirmEquipmentUseCase createConfirmEquipmentUseCase;

    @PostMapping(value = "/confirm-equipment")
    public Mono<ConfirmEquipmentResponse> confirmEquipment(@RequestBody ConfirmEquipmentRequest request) {
        return createConfirmEquipmentUseCase.create(request);
    }
}
