package com.sicom.ms.domain.model.error;

import java.util.Set;

public class BadRequestException extends ApplicationException {

    public BadRequestException(String code, String message, Set<ApplicationErrorDetail> details) {
        super(code, message, details);
    }

}
