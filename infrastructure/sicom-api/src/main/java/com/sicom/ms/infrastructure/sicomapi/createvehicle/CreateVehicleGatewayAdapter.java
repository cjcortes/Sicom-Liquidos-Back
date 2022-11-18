package com.sicom.ms.infrastructure.sicomapi.createvehicle;

import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleGateway;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleRequest;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class CreateVehicleGatewayAdapter  implements CreateVehicleGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<CreateVehicleResponse> createVehicle(CreateVehicleRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.post()
                .uri("WEBSERVICE/GCV_WS/AvanzarConsultarCrearVehiculo")
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> clientResponse.bodyToMono(CreateVehicleResponse.class ).map(v -> CreateVehicleResponse.builder()
                        .process(v.getProcess()).build()));
    }
}
