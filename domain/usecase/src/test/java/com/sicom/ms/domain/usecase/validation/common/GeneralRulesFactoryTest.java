package com.sicom.ms.domain.usecase.validation.common;

import com.sicom.ms.domain.usecase.validations.Rule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.sicom.ms.domain.usecase.validations.common.GeneralRulesFactory.cannotBeNull;
import static org.assertj.core.api.Assertions.assertThat;


class GeneralRulesFactoryTest {

    static Stream<String> blankStrings() {
        return Stream.of(null, null, null);
    }

    @ParameterizedTest
    @MethodSource("blankStrings")
    void cannotBeNullShouldBeInvalidIfParamIsNull(String nullField) {
        Rule<String> rule = cannotBeNull(value -> value, "test", "nullField");

        assertThat(rule.isInvalid(nullField))
                .isTrue();

        assertThat(rule.getReason().getCode())
                .isEqualTo("test.nullFieldCannotBeNull");

        assertThat(rule.getReason().getMessage())
                .isEqualTo("nullField cannot be null");
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "a", "ab", "abc"})
    void cannotBeNullShouldBeValidIfValueIsNotNull(String stringField) {
        Rule<String> rule = cannotBeNull(value -> value, "test", "stringField");

        assertThat(rule.isInvalid(stringField))
                .isFalse();
    }

}
