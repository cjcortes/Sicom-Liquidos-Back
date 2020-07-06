package com.sicom.ms.domain.usecase.validation;

import com.sicom.ms.domain.usecase.validations.Reason;
import com.sicom.ms.domain.usecase.validations.Rule;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;
import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {

    public static final String REASON_CODE = "myCode";
    public static final String REASON_MESSAGE = "myMessage";
    public static final Reason REASON = Reason.of(REASON_CODE, REASON_MESSAGE);


    @Test
    void shouldBeInvalidIfPredicateReturnsFalse() {
        Predicate<String> predicate = ouv -> true;

        var rule = Rule.of(REASON_CODE, REASON_MESSAGE, predicate);

        assertThat(rule.isInvalid(""))
                .isFalse();

        assertThat(rule.getReason())
                .isEqualTo(REASON);
    }

    @Test
    void shouldBeValidIfPredicateReturnsTrue() {
        Predicate<String> predicate = ouv -> false;

        var rule = Rule.of(REASON, predicate);

        assertThat(rule.isInvalid(""))
                .isTrue();

        assertThat(rule.getReason())
                .isEqualTo(REASON);
    }

}