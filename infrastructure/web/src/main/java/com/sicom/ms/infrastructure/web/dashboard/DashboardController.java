package com.sicom.ms.infrastructure.web.dashboard;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.consumptions.ConsumptionProduct;
import com.sicom.ms.domain.model.consumptions.ConsumptionsProductsFilters;
import com.sicom.ms.domain.model.dashboard.DashboardConsumptionQuota;
import com.sicom.ms.domain.model.dashboard.DashboardConsumptionQuotaFilters;
import com.sicom.ms.domain.model.dashboard.DashboardTotalsFilters;
import com.sicom.ms.domain.model.dashboard.DashboardTotal;
import com.sicom.ms.domain.usecase.consumptions.GetConsumptionsProductsUseCase;
import com.sicom.ms.domain.usecase.dashboard.GetConsumptionAndQuotaDashboardUseCase;
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
    private final GetConsumptionAndQuotaDashboardUseCase getConsumptionAndQuotaDashboardUseCase;

    @GetMapping(value = "/totals")
    public Flux<DashboardTotal> getTotals(@RequestParam(defaultValue = "-1") int product,
                                          @RequestParam(defaultValue = "-1") String orderType,
                                          @RequestParam(defaultValue = "-1") long startDate,
                                          @RequestParam(defaultValue = "-1") long endDate,
                                          Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> DashboardTotalsFilters.builder()
                        .sicomAgent((String) claims.get(SICOM_AGENT))
                        .product(product)
                        .orderType(orderType)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build())
                .flatMapMany(getTotalsDashboardUseCase::getTotals);
    }

    @GetMapping(value = "/consumption-quota")
    public Flux<DashboardConsumptionQuota> getConsumptionAndQuota(@RequestParam(defaultValue = "-1") int product,
                                                                  @RequestParam(defaultValue = "-1") String orderType,
                                                                  @RequestParam(defaultValue = "-1") int year,
                                                                  @RequestParam(defaultValue = "-1") int month,
                                                                  Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> DashboardConsumptionQuotaFilters.builder()
                        .sicomAgent((String) claims.get(SICOM_AGENT))
                        .product(product)
                        .orderType(orderType)
                        .year(year)
                        .month(month)
                        .build())
                .flatMapMany(getConsumptionAndQuotaDashboardUseCase::getConsumptionAndQuota);
    }
}