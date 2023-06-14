package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionType;
import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionTypeFilters;
import com.sicom.ms.domain.usecase.dashboardgases.revisiontype.GetRevisionTypeByFiltersUseCase;
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
public class RevisionTypeController {

    private final AuthenticationGateway authenticationGateway;

    private final GetRevisionTypeByFiltersUseCase getRevisionTypeByFiltersUseCase;

    @GetMapping(value = "/revision-type")
    public Flux<RevisionType> getRevisionType(@RequestParam(defaultValue = "-1") String revisionTypeId,
                                                 Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> RevisionTypeFilters.builder()
                        .revisionTypeId(revisionTypeId)
                        .build())
                .flatMapMany(getRevisionTypeByFiltersUseCase::getByFilters);
    }
}
