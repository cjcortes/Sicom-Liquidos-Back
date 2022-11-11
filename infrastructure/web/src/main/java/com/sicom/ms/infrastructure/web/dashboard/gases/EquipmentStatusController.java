package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatus;
import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatusFilters;
import com.sicom.ms.domain.usecase.dashboardgases.equipmentstatus.GetEquipmentStatusByFiltersUseCase;
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
public class EquipmentStatusController {

    private final AuthenticationGateway authenticationGateway;

    private final GetEquipmentStatusByFiltersUseCase getEquipmentStatusByFiltersUseCase;

    @GetMapping(value = "/equipment-status")
    public Flux<EquipmentStatus> getEquipmentStatus(@RequestParam(defaultValue = "-1") String equipmentStatusId,
                                                    @RequestParam(defaultValue = "-1") String equipmentTypeId,
                                                    Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> EquipmentStatusFilters.builder()
                        .equipmentStatusId(equipmentStatusId)
                        .equipmentTypeId(equipmentTypeId)
                        .build())
                .flatMapMany(getEquipmentStatusByFiltersUseCase::getByFilters);
    }
}
