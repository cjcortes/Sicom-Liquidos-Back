package com.sicom.ms.infrastructure.sicomapi.orders;

import com.sicom.ms.domain.model.orders.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class OPSimpleGatewayAdapter implements OPSimpleGateway {

    @Value("${app.sicom.api.url}")
    private String baseUrl;

    @Override
    public Mono<OPSimple> createOPSimple(OPSimpleRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.post()
                .uri("WEBSERVICE/liquidos/ops/OPS")
                .bodyValue(request)
                .exchange()
                .flatMap(response -> response.bodyToMono(OPSimple.class));
    }

    @Override
    public Mono<OPSimplePerform> confirmOPSimple(OPSimpleConfirmRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.post()
                .uri("/WEBSERVICE/liquidos/ops/PerformActivity")
                .bodyValue(request)
                .exchange()
                .flatMap(response -> response.bodyToMono(OPSimplePerform.class));
    }
}
