package com.sicom.ms.infrastructure.sicomapi.providers;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.model.providers.Provider;
import com.sicom.ms.domain.model.providers.ProvidersGateway;
import com.sicom.ms.infrastructure.sicomapi.plants.PlantDTO;
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
public class ProvidersGatewayAdapter implements ProvidersGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<Provider> getProvidersBySicomCode(String sicomCode) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri("WEBSERVICE/liquidos/ops/AgenteProveedor?codigoSicomSolicitante="+sicomCode+"")
                .retrieve()
                .bodyToFlux(ProviderDTO.class).map(p -> Provider.builder()
                        .applicantId(p.idSolicitante)
                        .applicantSicomCode(p.codSicomSolicitante)
                        .providerId(p.idProveedor)
                        .providerCommercialName(p.nombreComercialProveedor)
                        .providerSicomCode(p.codSicomProveedor)
                        .startContractDate(p.fechaInicioContrato)
                        .endContractDate(p.fechaFinContrato)
                        .build())
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));

    }
}
