package com.sicom.ms.infrastructure.sicomapi.garagecoordinates;

import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesFilters;
import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesGateway;
import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesResponse;
import com.sicom.ms.domain.model.dashboardgases.util.Error;
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
public class GarageCoordinatesGatewayAdapter implements GarageCoordinatesGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<GarageCoordinatesResponse> getBySicomCode(GarageCoordinatesFilters filters) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("WEBSERVICE/GCV_WS/ObtenerCoordenadasAgenteCertificador?codigoSicomTallerRevision="+filters.getSicomCode())
                .retrieve()
                .bodyToFlux(GarageCoordinatesResponse.class).map(p -> GarageCoordinatesResponse.builder()
                        .value(p.getValue())
                        .error(p.getError())
                        .build())
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Flux.just(GarageCoordinatesResponse.builder().error(Error.builder()
                                        .code("ERROR")
                                        .status(404)
                                        .build())
                                .build()) : Mono.error(ex))
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 400 ? Flux.just(GarageCoordinatesResponse.builder().error(Error.builder()
                                        .code("ERROR")
                                        .message("El Codigo del Agente Ingresado no se encuentra asociado a Ningun Taller. Valide el codigo SICOM del Taller.")
                                        .status(400)
                                        .build())
                                .build()) : Mono.error(ex))
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 500 ? Flux.just(GarageCoordinatesResponse.builder().error(Error.builder()
                                        .code("ERROR")
                                        .status(500)
                                        .build())
                                .build()) : Mono.error(ex));
    }
}
