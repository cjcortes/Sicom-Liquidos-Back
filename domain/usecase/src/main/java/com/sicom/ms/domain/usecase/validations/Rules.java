package com.sicom.ms.domain.usecase.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Rules<T> {

    private final List<Rule<T>> isolatedRules;


    @SafeVarargs
    public static <T> Rules<T> of(Rule<T>... rules) {
        return new Rules<>(Arrays.asList(rules));
    }

    public List<Rule<T>> asList() {
        return isolatedRules;
    }

}
