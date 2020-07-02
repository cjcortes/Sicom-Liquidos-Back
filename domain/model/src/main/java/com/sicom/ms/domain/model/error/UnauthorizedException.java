package com.sicom.ms.domain.model.error;

public class UnauthorizedException extends ApplicationException {

    public UnauthorizedException(String code, String message) {
        super(code, message);
    }

    public UnauthorizedException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
