package com.sicom.ms.domain.usecase.consumptions;

import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.model.consumptions.ConsumptionsGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.consumptions.GetConsumptionRules.GET_CONSUMPTION_RULES;

@RequiredArgsConstructor
public class GetConsumptionUseCase {

    private final ObjectValidator objectValidator;
    private final ConsumptionsGateway consumptionsGateway;

    public Mono<Consumption> get(String userCode) {
        objectValidator.validate(userCode, GET_CONSUMPTION_RULES)
                .throwBadRequestExceptionIfInvalid("get");
        return consumptionsGateway.get(userCode);
    }
}
