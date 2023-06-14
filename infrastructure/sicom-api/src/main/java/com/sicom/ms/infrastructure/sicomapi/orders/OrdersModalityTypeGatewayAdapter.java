package com.sicom.ms.infrastructure.sicomapi.orders;

import com.sicom.ms.domain.model.orders.OrderModalityType;
import com.sicom.ms.domain.model.orders.OrdersModalityTypeGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class OrdersModalityTypeGatewayAdapter implements OrdersModalityTypeGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<OrderModalityType> getOrderModalityType() {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri("WEBSERVICE/liquidos/ops/TipoModalidad")
                .retrieve()
                .bodyToFlux(OrderModalityTypeDTO.class).map(p -> OrderModalityType.builder()
                        .code(p.codigoModalidad)
                        .name(p.tipoModalidad)
                        .build())
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
    }
}
