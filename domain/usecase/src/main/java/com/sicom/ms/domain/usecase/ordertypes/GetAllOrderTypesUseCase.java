package com.sicom.ms.domain.usecase.ordertypes;

import com.sicom.ms.domain.model.ordertypes.OrderType;
import com.sicom.ms.domain.model.ordertypes.OrderTypesGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.ordertypes.GetAllOrderTypesRules.GET_ORDERS_TYPES_REQUEST_RULES;

@RequiredArgsConstructor
public class GetAllOrderTypesUseCase {

    private final ObjectValidator objectValidator;
    private final OrderTypesGateway orderTypesGateway;

    public Flux<OrderType> getAll(String userCode) {
        objectValidator.validate(userCode, GET_ORDERS_TYPES_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getAll");
        return orderTypesGateway.getAll(userCode);
    }
}
