package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceType;
import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceTypeFilters;
import com.sicom.ms.domain.usecase.dashboardgases.servicetype.GetServiceTypeByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/garage")
@RequiredArgsConstructor
public class ServiceTypeController {

    private final AuthenticationGateway authenticationGateway;

    private final GetServiceTypeByFiltersUseCase getServiceTypeByFiltersUseCase;

    @GetMapping(value = "/service-type")
    public Flux<ServiceType> getServiceType(@RequestParam(defaultValue = "-1") String vehicleServiceTypeId,
                                            Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> ServiceTypeFilters.builder()
                        .vehicleServiceTypeId(vehicleServiceTypeId)
                        .build())
                .flatMapMany(getServiceTypeByFiltersUseCase::getByFilters);
    }
}
