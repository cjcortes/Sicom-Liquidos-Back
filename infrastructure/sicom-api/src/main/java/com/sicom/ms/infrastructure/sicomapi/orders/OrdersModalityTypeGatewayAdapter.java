package com.sicom.ms.infrastructure.sicomapi.orders;

import com.sicom.ms.domain.model.orders.OrderModalityTypeEntitie;
import com.sicom.ms.domain.model.orders.OrdersModalityTypeGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class OrdersModalityTypeGatewayAdapter implements OrdersModalityTypeGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<OrderModalityTypeEntitie> getOrderModalityType() {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<OrderModalityTypeDTO> response = client.get()
                .uri("WEBSERVICE/liquidos/ops/TipoModalidad")
                .retrieve()
                .bodyToMono(OrderModalityTypeDTO.class);

        OrderModalityTypeDTO orderModalityTypeDTO = response.block();

        return Mono.just(OrderModalityTypeEntitie.builder()
                .key(orderModalityTypeDTO.entities.sCMP_TipoModalidad.key)
                .name(orderModalityTypeDTO.entities.sCMP_TipoModalidad.sNombre)
                .code(orderModalityTypeDTO.entities.sCMP_TipoModalidad.sCodigo)
                .build());

    }
}
