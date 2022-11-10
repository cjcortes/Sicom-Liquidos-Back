package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModel;
import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModelFilters;
import com.sicom.ms.domain.usecase.dashboardgases.equipmentmodel.GetEquipmentModelByFiltersUseCase;
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
public class EquipmentModelController {

    private final AuthenticationGateway authenticationGateway;

    private final GetEquipmentModelByFiltersUseCase getEquipmentModelByFiltersUseCase;

    @GetMapping(value = "/equipment-model")
    public Flux<EquipmentModel> getEquipmentModel(@RequestParam(defaultValue = "-1") String equipmentBrandId,
                                                     @RequestParam(defaultValue = "-1") String equipmentModelId,
                                                     Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> EquipmentModelFilters.builder()
                        .equipmentBrandId(equipmentBrandId)
                        .equipmentModelId(equipmentModelId)
                        .build())
                .flatMapMany(getEquipmentModelByFiltersUseCase::getByFilters);
    }
}
