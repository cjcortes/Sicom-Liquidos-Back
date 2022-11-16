package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnit;
import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnitFilters;
import com.sicom.ms.domain.usecase.dashboardgases.measurementunit.GetMeasurementUnitByFiltersUseCase;
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
public class MeasurementUnitController {

    private final AuthenticationGateway authenticationGateway;

    private final GetMeasurementUnitByFiltersUseCase getMeasurementUnitByFiltersUseCase;

    @GetMapping(value = "/measurement-unit")
    public Flux<MeasurementUnit> getMeasurementUnit(@RequestParam(defaultValue = "-1") String measurementUnitId,
                                                    Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> MeasurementUnitFilters.builder()
                        .measurementUnitId(measurementUnitId)
                        .build())
                .flatMapMany(getMeasurementUnitByFiltersUseCase::getByFilters);
    }
}
