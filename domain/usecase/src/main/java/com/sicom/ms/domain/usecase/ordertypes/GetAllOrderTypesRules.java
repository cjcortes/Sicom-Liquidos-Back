package com.sicom.ms.domain.usecase.ordertypes;

import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.NumberRulesFactory.cannotBeNegative;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllOrderTypesRules {
    public static final Rules<Integer> GET_ORDERS_TYPES_REQUEST_RULES = Rules.of(
            cannotBeNegative(value -> value, "userCode", "userCode")
    );
}
