package com.sicom.ms.infrastructure.web.consumptions;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.model.consumptions.ConsumptionProduct;
import com.sicom.ms.domain.model.consumptions.ConsumptionsProductsFilters;
import com.sicom.ms.domain.usecase.consumptions.GetConsumptionUseCase;
import com.sicom.ms.domain.usecase.consumptions.GetConsumptionsProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@RestController
@RequestMapping("/api/consumptions")
@RequiredArgsConstructor
public class ConsumptionsController {

    private final GetConsumptionUseCase getConsumptionUseCase;
    private final AuthenticationGateway authenticationGateway;
    private final GetConsumptionsProductsUseCase getConsumptionsProductsUseCase;

    @GetMapping
    public Mono<Consumption> get(Principal principal) {
        return authenticationGateway.getClaims(principal)
                .flatMap(claims -> getConsumptionUseCase.get((String) claims.get(SICOM_AGENT)));
    }

    @GetMapping(value = "/products")
    public Flux<ConsumptionProduct> getConsumptionsProducts(@RequestParam(defaultValue = "-1") int product,
                                                            @RequestParam(defaultValue = "-1") String orderType,
                                                            @RequestParam(defaultValue = "-1") long startDate,
                                                            @RequestParam(defaultValue = "-1") long endDate,
                                                            Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> ConsumptionsProductsFilters.builder()
                        .sicomAgent((String) claims.get(SICOM_AGENT))
                        .product(product)
                        .orderType(orderType)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build())
                .flatMapMany(getConsumptionsProductsUseCase::getConsumptionsProducts);
    }
}
