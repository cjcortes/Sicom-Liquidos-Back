package com.sicom.ms.infrastructure.web.plants;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.plants.ReceiptPlant;
import com.sicom.ms.domain.usecase.plants.GetAllPlantsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@RestController
@RequestMapping("/api/receipt-plants")
@RequiredArgsConstructor
public class PlantsController {
    private final GetAllPlantsUseCase getAllPlantsUseCase;
    private final AuthenticationGateway authenticationGateway;

    @GetMapping(value = "/get")
    public Flux<ReceiptPlant> getReceiptPlants(Principal principal) {
        return authenticationGateway.getClaims(principal)
                .map(claims -> (String) claims.get(SICOM_AGENT))
                .flatMapMany(getAllPlantsUseCase::get);
    }
}
