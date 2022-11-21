package com.sicom.ms.infrastructure.sicomapi.registerindividualrevision;

import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionGateway;
import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionRequest;
import com.sicom.ms.domain.model.dashboardgases.registerindividualrevision.RegisterIndividualRevisionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class RegisterIndividualRevisionGatewayAdapter implements RegisterIndividualRevisionGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<RegisterIndividualRevisionResponse> createRegisterIndividualRevision(RegisterIndividualRevisionRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.post()
                .uri("WEBSERVICE/GCV_WS/AvanzarRevisionIndividual")
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> clientResponse.bodyToMono(RegisterIndividualRevisionResponse.class ).map(v -> RegisterIndividualRevisionResponse.builder()
                        .process(v.getProcess()).build()));
    }
}
