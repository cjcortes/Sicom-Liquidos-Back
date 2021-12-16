package com.sicom.ms.infrastructure.sicomapi.orders;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.orders.*;
import com.sicom.ms.infrastructure.sicomapi.vehicles.VehiclesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class OPSimpleGatewayAdapter implements OPSimpleGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<OPSimple> createOPSimple(OPSimpleRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<OPSimple> result = client.post()
                .uri("WEBSERVICE/liquidos/ops/OPS")
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> {
                    if ( clientResponse.statusCode() == HttpStatus.BAD_REQUEST ) {
                        return clientResponse.bodyToMono(OPSimpleError.class)
                                .flatMap(errorDetails ->
                                        Mono.error(new BadRequestException("400", errorDetails.process.processError.errorMessage, null))
                                );
                    }
                    return clientResponse.bodyToMono(OPSimple.class );
                } );

        return result;
    }

    @Override
    public Mono<OPSimplePerform> confirmOPSimple(OPSimpleConfirmRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<OPSimplePerform> result = client.post()
                .uri("/WEBSERVICE/liquidos/ops/PerformActivity")
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> {
                    //if ( clientResponse.statusCode() == HttpStatus.BAD_REQUEST  ) {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorDetails ->
                                        Mono.error(new BadRequestException("400", "errorDetails.process.processError.errorMessage", null)
                                        ));
                    //}
                    //return clientResponse.bodyToMono(OPSimplePerform.class );
                } );

        return result;
    }
}

class ProcessError{
    public String errorCode;
    public String errorMessage;
}

class Process{
    public String processId;
    public String processRadNumber;
    public ProcessError processError;
    @JsonProperty("CurrentWorkItems")
    public String currentWorkItems;
}

class Root{
    public Process process;
}