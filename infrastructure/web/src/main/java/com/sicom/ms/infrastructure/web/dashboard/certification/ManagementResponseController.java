package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponse;
import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponseFilters;
import com.sicom.ms.domain.model.dashboardgases.vehiclebrand.VehicleBrand;
import com.sicom.ms.domain.model.dashboardgases.vehiclebrand.VehicleBrandFilters;
import com.sicom.ms.domain.usecase.dashboardgases.managementresponse.GetManagementResponseByFiltersUseCase;
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
public class ManagementResponseController {

    private final AuthenticationGateway authenticationGateway;

    private final GetManagementResponseByFiltersUseCase getManagementResponseByFiltersUseCase;

    @GetMapping(value = "/management-response")
    public Flux<ManagementResponse> getManagementResponse(@RequestParam(defaultValue = "-1") String managementResponseId,
                                                          Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> ManagementResponseFilters.builder()
                        .managementResponseId(managementResponseId)
                        .build())
                .flatMapMany(getManagementResponseByFiltersUseCase::getByFilters);
    }
}
