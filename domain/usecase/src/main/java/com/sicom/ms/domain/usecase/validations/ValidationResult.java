package com.sicom.ms.domain.usecase.validations;

import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import lombok.Value;

import java.util.Set;
import java.util.stream.Collectors;


@Value
public class ValidationResult {

    Set<Reason> reasons;


    public boolean isValid() {
        return reasons.isEmpty();
    }

    public void throwBadRequestExceptionIfInvalid(String context) {
        if (!isValid()) {
            var code = String.format("%s.error.badRequest", context);
            var description = String.format("%s bad request", context);

            throw new BadRequestException(code, description, mapReasonsToDetails());
        }
    }

    private Set<ApplicationErrorDetail> mapReasonsToDetails() {
        return reasons.stream()
                .map(reason -> new ApplicationErrorDetail(reason.getCode(), reason.getMessage()))
                .collect(Collectors.toSet());
    }

}
