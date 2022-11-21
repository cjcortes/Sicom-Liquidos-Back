package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusFilters;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusResponse;
import com.sicom.ms.domain.usecase.dashboardgases.validateequipmentstatus.GetValidateEquipmentStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class ValidateEquipmentStatusController {

    private final GetValidateEquipmentStatusUseCase getValidateEquipmentStatusUseCase;

    @GetMapping(value = "/validate-equipment-status")
    public Flux<ValidateEquipmentStatusResponse> getValidateEquipmentStatus(@RequestParam(defaultValue = "-1") String serial,
                                                                               @RequestParam(defaultValue = "-1") String idTipoEquipo,
                                                                               @RequestParam(defaultValue = "-1") String idVehiculoBPM) {

        return getValidateEquipmentStatusUseCase.getByFilters(ValidateEquipmentStatusFilters.builder()
                .serial(serial)
                .idTipoEquipo(idTipoEquipo)
                .idVehiculoBPM(idVehiculoBPM).build());
    }
}
