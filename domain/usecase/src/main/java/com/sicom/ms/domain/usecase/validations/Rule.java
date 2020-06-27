package com.sicom.ms.domain.usecase.validations;

import java.util.function.Predicate;


public class Rule<T> {

    private final Reason reason;
    private final Predicate<T> predicate;


    private Rule(Reason reason, Predicate<T> predicate) {
        this.reason = reason;
        this.predicate = predicate;
    }

    public boolean isInvalid(T object) {
        return !predicate.test(object);
    }

    public Reason getReason() {
        return reason;
    }

    public static <T> Rule<T> of(Reason reason, Predicate<T> predicate) {
        return new Rule<>(reason, predicate);
    }

    public static <T> Rule<T> of(String code, String message, Predicate<T> predicate) {
        return of(Reason.of(code, message), predicate);
    }

}
