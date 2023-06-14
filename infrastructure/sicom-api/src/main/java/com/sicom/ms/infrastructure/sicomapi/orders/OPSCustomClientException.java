package com.sicom.ms.infrastructure.sicomapi.orders;

import com.sicom.ms.domain.model.orders.OPSimple;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


class OPSCustomClientException extends ResponseStatusException {
    private final HttpStatus status;
    private final OPSimple details;

    OPSCustomClientException(HttpStatus status, OPSimple details) {
        super(status, "test");
        this.status = status;
        this.details = details;
    }

    public HttpStatus getStatus() {
        return status;
    }

    /*public OPSimpleError getDetails() {
        return details;
    }*/
}