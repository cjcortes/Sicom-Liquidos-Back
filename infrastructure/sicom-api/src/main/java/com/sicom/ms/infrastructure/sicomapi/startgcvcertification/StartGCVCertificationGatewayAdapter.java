package com.sicom.ms.infrastructure.sicomapi.startgcvcertification;

import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationGateway;
import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationRequest;
import com.sicom.ms.domain.model.dashboardgases.startgcvcertification.StartGCVCertificationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class StartGCVCertificationGatewayAdapter implements StartGCVCertificationGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<StartGCVCertificationResponse> createStarGCVCertification(StartGCVCertificationRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.post()
                .uri("WEBSERVICE/GCV_WS/IniciarCertificacionGCV")
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> clientResponse.bodyToMono(StartGCVCertificationResponse.class ).map( v -> StartGCVCertificationResponse.builder()
                        .process(v.getProcess()).build()));

    }
}
