package com.sicom.ms.domain.usecase.consumptions;

import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetConsumptionRules {
    public static final Rules<String> GET_CONSUMPTION_RULES = Rules.of(
            cannotBeNull(value -> value, "sicomAgent", "sicomAgent")
    );
}
