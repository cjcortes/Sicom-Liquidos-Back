package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrand;
import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrandFilters;
import com.sicom.ms.domain.usecase.dashboardgases.equipmentbrand.GetEquipmentBrandByFiltersUseCase;
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
public class EquipmentBrandController {

    private final AuthenticationGateway authenticationGateway;

    private final GetEquipmentBrandByFiltersUseCase getEquipmentBrandByFiltersUseCase;

    @GetMapping(value = "/equipment-brand")
    public Flux<EquipmentBrand> getEquipmentBrand(@RequestParam(defaultValue = "-1") String equipmentBrandId,
                                                     @RequestParam(defaultValue = "-1") String equipmentTypeId,
                                                     Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> EquipmentBrandFilters.builder()
                        .equipmentBrandId(equipmentBrandId)
                        .equipmentTypeId(equipmentTypeId)
                        .build())
                .flatMapMany(getEquipmentBrandByFiltersUseCase::getByFilters);
    }
}
