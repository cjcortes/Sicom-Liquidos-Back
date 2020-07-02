package com.sicom.ms.domain.model.error;


public class NotFoundException extends ApplicationException {

    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
