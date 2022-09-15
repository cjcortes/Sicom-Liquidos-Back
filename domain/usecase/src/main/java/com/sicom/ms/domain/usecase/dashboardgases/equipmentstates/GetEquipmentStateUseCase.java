package com.sicom.ms.domain.usecase.dashboardgases.equipmentstates;

import com.sicom.ms.domain.model.dashboardgases.equipmentstates.EquipmentState;
import com.sicom.ms.domain.model.dashboardgases.equipmentstates.EquipmentStatesGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetEquipmentStateUseCase {

    private final EquipmentStatesGateway equipmentStatesGateway;

    public Flux<EquipmentState> getEquipmentState(){
        return equipmentStatesGateway.getEquipmentState();
    }
}
