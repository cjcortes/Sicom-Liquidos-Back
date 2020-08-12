package com.sicom.ms.infrastructure.web.consumptions;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.usecase.consumptions.GetConsumptionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@RestController
@RequestMapping("/api/consumptions")
@RequiredArgsConstructor
public class ConsumptionsController {

    private final GetConsumptionUseCase getConsumptionUseCase;
    private final AuthenticationGateway authenticationGateway;

    @GetMapping
    public Mono<Consumption> get(Principal principal) {
        return authenticationGateway.getClaims(principal)
                .flatMap(claims -> getConsumptionUseCase.get((String) claims.get(SICOM_AGENT)));
    }

}
