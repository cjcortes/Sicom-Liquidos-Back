package com.sicom.ms.infrastructure.sicomapi.orders;

import com.sicom.ms.domain.model.orders.OPSimpleError;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.server.ResponseStatusException;


class OPSCustomClientException extends ResponseStatusException {
    private final HttpStatus status;
    private final OPSimpleError details;

    OPSCustomClientException(HttpStatus status, OPSimpleError details) {
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