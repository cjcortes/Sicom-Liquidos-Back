package com.sicom.ms.infrastructure.sicomapi.agents;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.model.agents.AgentsGateway;
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
public class AgentsGatewayAdapter implements AgentsGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<Agent> getAgentById(String agentId) {

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("WEBSERVICE/liquidos/ops/Agente?codigosicom="+agentId+"")
                .retrieve()
                .bodyToFlux(AgentDTO.class).map(a -> Agent.builder()
                        .agentId(a.idAgente)
                        .commercialName(a.nombreComercial)
                        .sicomCode(a.codigoSicom)
                        .nit(a.nit)
                        .agentSubType(a.subTipoAgente)
                        .agentType(a.tipoAgente)
                        .department(a.departamento)
                        .municipality(a.municipio)
                        .addressCorrespondence(a.direccionCorrespondencia)
                        .isFrontierZone(a.zonaFrontera)
                        .build())
                .onErrorResume(WebClientResponseException.class,
                        ex -> ex.getRawStatusCode() == 404 ? Flux.empty() : Mono.error(ex));
    }
}
