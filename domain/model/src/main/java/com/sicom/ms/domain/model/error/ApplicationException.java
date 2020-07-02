package com.sicom.ms.domain.model.error;

import lombok.Getter;

import java.util.Collections;
import java.util.Set;

@Getter
public class ApplicationException extends RuntimeException {

    private final String code;
    private final Set<ApplicationErrorDetail> details;


    public ApplicationException(String code, String message) {
        super(message);
        this.code = code;
        this.details = Collections.emptySet();
    }

    public ApplicationException(String code, String message, Set<ApplicationErrorDetail> details) {
        super(message);
        this.code = code;
        this.details = details;
    }

    public ApplicationException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.details = Collections.emptySet();
    }

}
