package com.sicom.ms.domain.usecase.validation;

import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import com.sicom.ms.domain.usecase.validations.Reason;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectValidatorTest {

    private static final Reason TOO_SHORT_REASON = Reason.of("tooShort", "String too short");

    private static final Rules<String> LENGTH_STRING_RULES = Rules.of(
            Rule.of(TOO_SHORT_REASON, ouv -> ouv.length() > 10),
            Rule.of("toLong", "String too long", ouv -> ouv.length() < 5)
    );

    private static final Rules<String> ALWAYS_VALID_RULES = Rules.of(
            Rule.of("valid", "is valid", ouv -> true),
            Rule.of("test", "tested", ouv -> true)
    );


    private final ObjectValidator objectValidator = new ObjectValidator();


    @Test
    void shouldReturnInvalidValidationResultWithReasons() {
        var objectUnderValidation = "ouv";

        var result = objectValidator.validate(objectUnderValidation, LENGTH_STRING_RULES);

        assertThat(result.isValid())
                .as("Validation result should be invalid")
                .isFalse();

        assertThat(result.getReasons())
                .containsExactly(TOO_SHORT_REASON);
    }

    @Test
    void shouldReturnValidValidationResultWithEmptyReasons() {
        var objectUnderValidation = "ouv";

        var result = objectValidator.validate(objectUnderValidation, ALWAYS_VALID_RULES);

        assertThat(result.isValid())
                .as("Validation result should be valid")
                .isTrue();

        assertThat(result.getReasons())
                .isEmpty();
    }

}