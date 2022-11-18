package com.sicom.ms.infrastructure.sicomapi.certificationvehicle;

import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleGateway;
import com.sicom.ms.domain.model.dashboardgases.certificationvehicle.CertificationVehicleResponse;
import com.sicom.ms.domain.model.dashboardgases.util.Error;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class CertificationVehicleGatewayAdapter implements CertificationVehicleGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<CertificationVehicleResponse> getCertificationVehicle(CertificationVehicleFilters filters) {

        String param = "";

        if(!filters.getPlaca().equals(null) && !filters.getPlaca().equals("")){
            param = "placa=" + filters.getPlaca();
        }

        if(!filters.getChip().equals(null) && !filters.getChip().equals("")){
            param = "chip=" + filters.getChip();
        }

        if(!filters.getVin().equals(null) && !filters.getVin().equals("")){
            param = "VIN=" + filters.getVin();
        }

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .build();
        return client.get()
                .uri("WEBSERVICE/GCV_WS/BuscarVehiculoCertificacion?" + param)
                .retrieve()
                .bodyToFlux(CertificationVehicleResponse.class).map(p -> CertificationVehicleResponse.builder()
                        .valueDetail(p.getValueDetail())
                        .error(p.getError())
                        .build())
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Flux.just(CertificationVehicleResponse.builder().error(Error.builder()
                                        .code("ERROR")
                                        .status(404)
                                        .build())
                                .build()) : Mono.error(ex))
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 400 || ex.getRawStatusCode() == 500? Flux.just(CertificationVehicleResponse.builder().error(Error.builder()
                                        .code("ERROR")
                                                .message("Para realizar la busqueda la Placa ingresada debe tener 5 o 6 caracteres entre numeros y letras Mayuscula (No se permiten caracteres especiales)")
                                        .status(ex.getRawStatusCode())
                                        .build())
                                .build()) : Mono.error(ex));
    }
}
