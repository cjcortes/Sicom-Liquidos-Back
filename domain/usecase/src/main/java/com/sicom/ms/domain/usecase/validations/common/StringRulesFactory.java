package com.sicom.ms.domain.usecase.validations.common;

import com.sicom.ms.domain.usecase.validations.Rule;

import java.util.function.Function;
import java.util.function.ToIntFunction;

import static com.sicom.ms.domain.model.common.StringOperations.isNotEmpty;

public class StringRulesFactory {

    public static <T> Rule<T> cannotBeEmpty(Function<T, String> mapper, String context, String field) {
        var code = String.format("%s.%sCannotBeEmpty", context, field);
        var message = String.format("%s cannot be empty", field);

        return Rule.of(code, message, object -> isNotEmpty(mapper.apply(object)));
    }

    private StringRulesFactory() {
    }

}
