package com.sicom.ms.infrastructure.sicomapi;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.model.agents.AgentsGateway;
import com.sicom.ms.domain.model.plants.Plant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Repository
public class AgentsGatewayAdapter implements AgentsGateway {
    //@Value("${app.sicom.api.url}")
    @Value("http://192.168.76.151/")
    private String baseUrl;

    @Override
    public Flux<Agent> getAllAgents() {

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("WEBSERVICE/liquidos/ops/Agente/all")
                .retrieve()
                .bodyToFlux(AgentDTO.class).map(a -> Agent.builder()
                        .idAgente(a.idAgente)
                        .nombreComercial(a.nombreComercial)
                        .codigoSicom(a.codigoSicom)
                        .build());
    }
}