package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderType;
import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderTypeFilters;
import com.sicom.ms.domain.usecase.dashboardgases.cylindertype.GetCylinderTypeByFiltersUseCase;
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
public class CylinderTypeController {

    private final AuthenticationGateway authenticationGateway;

    private final GetCylinderTypeByFiltersUseCase getCylinderTypeByFiltersUseCase;

    @GetMapping(value = "/cylinder-type")
    public Flux<CylinderType> getCylinderType(@RequestParam(defaultValue = "-1") String cylinderTypeId,
                                              Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> CylinderTypeFilters.builder()
                        .cylinderTypeId(cylinderTypeId)
                        .build())
                .flatMapMany(getCylinderTypeByFiltersUseCase::getByFilters);
    }
}
