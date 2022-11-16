package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentType;
import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentTypeFilters;
import com.sicom.ms.domain.usecase.dashboardgases.equipmenttype.GetEquipmentTypeByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class EquipmentTypeController {

    private final AuthenticationGateway authenticationGateway;

    private final GetEquipmentTypeByFiltersUseCase getEquipmentTypeByFiltersUseCase;

    @GetMapping(value = "/equipment-type")
    public Flux<EquipmentType> getEquipmentType(@RequestParam(defaultValue = "-1") String equipmentTypeId,
                                                 Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> EquipmentTypeFilters.builder()
                        .equipmentTypeId(equipmentTypeId)
                        .build())
                .flatMapMany(getEquipmentTypeByFiltersUseCase::getByFilters);
    }
}
