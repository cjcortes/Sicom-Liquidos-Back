package com.sicom.ms.domain.usecase.validations.common;

import com.sicom.ms.domain.usecase.validations.Rule;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class GeneralRulesFactory {

    public static <T> Rule<T> cannotBeNull(Function<T, Object> mapper, String context, String field) {
        var code = String.format("%s.%sCannotBeNull", context, field);
        var message = String.format("%s cannot be null", field);

        return Rule.of(code, message, object -> mapper.apply(object) != null);
    }

    private GeneralRulesFactory() {
    }

}
