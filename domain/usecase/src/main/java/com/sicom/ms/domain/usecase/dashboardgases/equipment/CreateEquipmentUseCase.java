package com.sicom.ms.domain.usecase.dashboardgases.equipment;

import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentRequest;
import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentResponse;
import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateEquipmentUseCase {
    private final EquipmentsGateway equipmentsGateway;

    public Mono<EquipmentResponse> create(EquipmentRequest request){
        return equipmentsGateway.createEquipment(request);
    }
}
