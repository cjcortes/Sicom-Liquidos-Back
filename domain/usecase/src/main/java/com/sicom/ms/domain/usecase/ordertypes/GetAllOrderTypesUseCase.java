package com.sicom.ms.domain.usecase.ordertypes;

import com.sicom.ms.domain.model.orders.OrderType;
import com.sicom.ms.domain.model.orders.OrderTypesGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.ordertypes.GetAllOrderTypesRules.GET_ORDERS_TYPES_REQUEST_RULES;

@RequiredArgsConstructor
public class GetAllOrderTypesUseCase {

    private final ObjectValidator objectValidator;
    private final OrderTypesGateway orderTypesGateway;

    public Flux<OrderType> getAll(int userCode) {
        objectValidator.validate(userCode, GET_ORDERS_TYPES_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getAll");
        return orderTypesGateway.getAll(userCode);
    }
}
