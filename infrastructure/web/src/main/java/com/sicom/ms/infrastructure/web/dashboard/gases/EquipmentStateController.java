package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.equipmentstates.EquipmentState;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicleFilters;
import com.sicom.ms.domain.usecase.dashboardgases.equipmentstates.GetEquipmentStateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/garage")
@RequiredArgsConstructor
public class EquipmentStateController {

    private final AuthenticationGateway authenticationGateway;

    private final GetEquipmentStateUseCase getEquipmentStateUseCase;

    @GetMapping(value = "/equipment-state")
    public Flux<EquipmentState> getEquipmentState(Principal principal) {

        return authenticationGateway.getClaims(principal)
                .flatMapMany(claims -> getEquipmentStateUseCase.getEquipmentState());
    }
}
