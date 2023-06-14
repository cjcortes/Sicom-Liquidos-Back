package com.sicom.ms.domain.usecase.dashboardgases.confirmequipment;

import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentGateway;
import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentRequest;
import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateConfirmEquipmentUseCase {

    private final ConfirmEquipmentGateway confirmEquipmentGateway;

    public Mono<ConfirmEquipmentResponse> create(ConfirmEquipmentRequest request){
        return confirmEquipmentGateway.createConfirmEquipment(request);
    }
}
