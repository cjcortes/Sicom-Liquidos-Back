package com.sicom.ms.infrastructure.web.dashboard.gases;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocation;
import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocationFilters;
import com.sicom.ms.domain.usecase.dashboardgases.agentlocations.GetAgentLocationsByFiltersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/dashboard-gases/agent-location")
@RequiredArgsConstructor
public class AgentLocationController {

    private final AuthenticationGateway authenticationGateway;

    private final GetAgentLocationsByFiltersUseCase getAgentLocationsByFiltersUseCase;

    @GetMapping
    public Flux<AgentLocation> getAgentLocations(@RequestParam(defaultValue = "-1") String departmentCode,
                                             @RequestParam(defaultValue = "-1") String cityCode,
                                             @RequestParam(defaultValue = "-1") String gcvType,
                                             Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> AgentLocationFilters.builder()
                        .departmentCode(departmentCode)
                        .cityCode(cityCode)
                        .gcvType(gcvType)
                        .build())
                .flatMapMany(getAgentLocationsByFiltersUseCase::getByFilters);
    }
}
