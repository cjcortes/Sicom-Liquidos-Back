package com.sicom.ms.domain.usecase.ordertypes;

import com.sicom.ms.domain.model.orders.OrderType;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;
import static com.sicom.ms.domain.usecase.validations.common.NumberRulesFactory.cannotBeNegative;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllOrderTypesRules {
    public static final Rules<Integer> GET_ORDERS_TYPES_REQUEST_RULES = Rules.of(
            cannotBeNegative(value -> value, "userCode", "userCode")
    );
}
