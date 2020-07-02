package com.sicom.ms.domain.usecase.validations;

import com.sicom.ms.domain.model.di.Injectable;

import java.util.stream.Collectors;


@Injectable
public class ObjectValidator {

    public <T> ValidationResult validate(T object, Rules<T> rules) {
        var reasons = rules.asList().stream()
                .filter(rule -> rule.isInvalid(object))
                .map(Rule::getReason)
                .collect(Collectors.toSet());

        return new ValidationResult(reasons);
    }

}
