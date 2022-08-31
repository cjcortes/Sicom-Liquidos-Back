package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicle;
import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicleFilters;
import com.sicom.ms.domain.usecase.dashboardgases.authorizevehicles.GetAuthorizeVehiclesByFiltersUseCase;
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
public class AuthorizeVehicleController {

    private final AuthenticationGateway authenticationGateway;

    private final GetAuthorizeVehiclesByFiltersUseCase getAuthorizeVehiclesByFiltersUseCase;

    @GetMapping(value = "/authorize-vehicles")
    public Flux<AuthorizeVehicle> getAuthorizeVehicles(@RequestParam(defaultValue = "-1") String departmentCode,
                                                     @RequestParam(defaultValue = "-1") String cityCode,
                                                     @RequestParam(defaultValue = "-1") String certifierSicomCode,
                                                     Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> AuthorizeVehicleFilters.builder()
                        .departmentCode(departmentCode)
                        .cityCode(cityCode)
                        .certifierSicomCode(certifierSicomCode)
                        .build())
                .flatMapMany(getAuthorizeVehiclesByFiltersUseCase::getByFilters);
    }
}
