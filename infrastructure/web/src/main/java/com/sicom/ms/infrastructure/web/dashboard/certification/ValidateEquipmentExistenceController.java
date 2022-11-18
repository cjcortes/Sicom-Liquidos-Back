package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceFilters;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceResponse;
import com.sicom.ms.domain.usecase.dashboardgases.validateequipmentexistence.GetValidateEquipmentExistenceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class ValidateEquipmentExistenceController {

    private final GetValidateEquipmentExistenceUseCase getValidateEquipmentExistenceUseCase;

    @GetMapping(value = "/validate-equipment-existence")
    public Flux<ValidateEquipmentExistenceResponse> getValidateEquipmentExistence(@RequestParam(defaultValue = "") String serial,
                                                                           @RequestParam(defaultValue = "") String idTipoEquipo) {

        return getValidateEquipmentExistenceUseCase.getByFilters(ValidateEquipmentExistenceFilters.builder()
                .serial(serial)
                .idTipoEquipo(idTipoEquipo).build());
    }
}
