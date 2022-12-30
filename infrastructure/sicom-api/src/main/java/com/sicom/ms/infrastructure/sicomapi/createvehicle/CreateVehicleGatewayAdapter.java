package com.sicom.ms.infrastructure.sicomapi.createvehicle;

import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleGateway;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleRequest;
import com.sicom.ms.domain.model.dashboardgases.createvehicle.CreateVehicleResponse;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Collections;

@Repository
public class CreateVehicleGatewayAdapter  implements CreateVehicleGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<CreateVehicleResponse> createVehicle(CreateVehicleRequest request) {

        WebClient client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(25 * 1024 * 1024)).build())
                .build();

        return client.post()
                .uri("WEBSERVICE/GCV_WS/AvanzarConsultarCrearVehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> clientResponse.bodyToMono(CreateVehicleResponse.class).map(v -> CreateVehicleResponse.builder()
                        .process(v.getProcess()).build()));
    }

}
