package com.sicom.ms.infrastructure.web.dashboard;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboard.DashboardFilters;
import com.sicom.ms.domain.model.dashboard.DashboardTotal;
import com.sicom.ms.domain.usecase.dashboard.GetTotalsDashboardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;


@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final AuthenticationGateway authenticationGateway;

    private final GetTotalsDashboardUseCase getTotalsDashboardUseCase;

    @GetMapping(value = "/totals")
    public Flux<DashboardTotal> getTotals(@RequestParam(defaultValue = "-1") int product,
                                          @RequestParam(defaultValue = "-1") String orderType,
                                          @RequestParam(defaultValue = "-1") long startDate,
                                          @RequestParam(defaultValue = "-1") long endDate,
                                          Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> DashboardFilters.builder()
                        .sicomAgent((String) claims.get(SICOM_AGENT))
                        .product(product)
                        .orderType(orderType)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build())
                .flatMapMany(getTotalsDashboardUseCase::getTotals);
    }
}