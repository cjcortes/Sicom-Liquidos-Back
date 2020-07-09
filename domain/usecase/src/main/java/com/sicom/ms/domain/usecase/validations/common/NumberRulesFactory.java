package com.sicom.ms.domain.usecase.validations.common;

import com.sicom.ms.domain.usecase.validations.Rule;

import java.util.function.ToIntFunction;

public class NumberRulesFactory {

    public static <T> Rule<T> cannotBeNegative(ToIntFunction<T> mapper, String context, String field) {
        var code = String.format("%s.%sCannotBeNegative", context, field);
        var message = String.format("%s cannot be negative", field);

        return Rule.of(code, message, object -> mapper.applyAsInt(object) >= 0);
    }

    private NumberRulesFactory() {

    }

}
