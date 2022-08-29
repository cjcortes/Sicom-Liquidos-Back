package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSite;
import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSiteFilters;
import com.sicom.ms.domain.usecase.dashboardgases.totalsites.GetTotalSitesByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/total-sites")
@RequiredArgsConstructor
public class TotalSitesController {

    private final AuthenticationGateway authenticationGateway;

    private final GetTotalSitesByFiltersUseCase getTotalSitesByFiltersUseCase;

    @GetMapping
    public Flux<TotalSite> getTotalSites(@RequestParam(defaultValue = "-1") String departmentCode,
                                         @RequestParam(defaultValue = "-1") String cityCode,
                                         @RequestParam(defaultValue = "-1") String conversionType,
                                         Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> TotalSiteFilters.builder()
                        .departmentCode(departmentCode)
                        .cityCode(cityCode)
                        .conversionType(conversionType)
                        .build())
                .flatMapMany(getTotalSitesByFiltersUseCase::getByFilters);
    }
}
