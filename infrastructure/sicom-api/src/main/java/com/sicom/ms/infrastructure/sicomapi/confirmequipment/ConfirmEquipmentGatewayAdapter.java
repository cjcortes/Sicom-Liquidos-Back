package com.sicom.ms.infrastructure.sicomapi.confirmequipment;

import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentGateway;
import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentRequest;
import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipmentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class ConfirmEquipmentGatewayAdapter implements ConfirmEquipmentGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<ConfirmEquipmentResponse> createConfirmEquipment(ConfirmEquipmentRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.post()
                .uri("WEBSERVICE/GCV_WS/ConfirmarEquipo")
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> clientResponse.bodyToMono(ConfirmEquipmentResponse.class ).map(v -> ConfirmEquipmentResponse.builder()
                        .value(v.getValue())
                        .error(v.getError()).build()));
    }
}
