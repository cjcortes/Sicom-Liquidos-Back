package com.sicom.ms.domain.model.error;

import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value(staticConstructor = "of")
public class ApplicationError {

    public static final ApplicationError INTERNAL_ERROR =
            ApplicationError.of("internalError", "Internal error", Collections.emptySet());

    String code;
    String message;
    Set<ApplicationErrorDetail> details;

    public static ApplicationError fromApplicationException(ApplicationException e) {
        return ApplicationError.of(e.getCode(), e.getLocalizedMessage(), e.getDetails());
    }

}
