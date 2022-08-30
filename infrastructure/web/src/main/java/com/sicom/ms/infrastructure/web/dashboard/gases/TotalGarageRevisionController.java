package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevision;
import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevisionFilters;
import com.sicom.ms.domain.usecase.dashboardgases.totalconvertvehicles.GetTotalConvertVehiclesByFiltersUseCase;
import com.sicom.ms.domain.usecase.dashboardgases.totalgaragesrevisions.GetTotalGarageRevisionsByFilterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/garages-revisions")
@RequiredArgsConstructor
public class TotalGarageRevisionController {

    private final AuthenticationGateway authenticationGateway;

    private final GetTotalGarageRevisionsByFilterUseCase getTotalGarageRevisionsByFilterUseCase;

    @GetMapping
    public Flux<TotalGarageRevision> getTotalGarageRevisions(@RequestParam(defaultValue = "-1") String garageSicomCode,
                                                             @RequestParam(defaultValue = "-1") String startDate,
                                                             @RequestParam(defaultValue = "-1") String endDate,
                                                             Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> TotalGarageRevisionFilters.builder()
                        .garageSicomCode(garageSicomCode)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build())
                .flatMapMany(getTotalGarageRevisionsByFilterUseCase::getByFilters);
    }
}
