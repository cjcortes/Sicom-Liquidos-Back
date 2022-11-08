package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusive;
import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusiveFilters;
import com.sicom.ms.domain.usecase.dashboardgases.dualexclusives.GetDualExclusiveByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/certifier")
@RequiredArgsConstructor
public class DualExclusiveController {

    private final AuthenticationGateway authenticationGateway;

    private final GetDualExclusiveByFiltersUseCase getDualExclusiveByFiltersUseCase;

    @GetMapping(value = "/dual-exclusive")
    public Flux<DualExclusive> getDualExclusive(@RequestParam(defaultValue = "-1") String dualExclusiveId,
                                               Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> DualExclusiveFilters.builder()
                        .dualExclusiveId(dualExclusiveId)
                        .build())
                .flatMapMany(getDualExclusiveByFiltersUseCase::getByFilters);
    }
}
