package com.sicom.ms.infrastructure.sicomapi.plants;

import com.sicom.ms.domain.model.agents.Agent;
import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.model.plants.PlantsGateway;
import com.sicom.ms.infrastructure.sicomapi.agents.AgentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PlantsGatewayAdapter implements PlantsGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<Plant> getPlantsByAgentId(String agentId) {

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri("WEBSERVICE/liquidos/ops/Plantas?id_Agente="+agentId+"")
                .retrieve()
                .bodyToFlux(PlantDTO.class).map(p -> Plant.builder()
                        .idPlant(p.idPlanta)
                        .plantName(p.nombrePlanta)
                        .status(p.estado)
                        .idAgent(p.idAgente)
                        .nominalTotalCapacity(p.capacidad_Total_Nominal)
                        .totalOperatingCapacity(p.capacidad_Total_Operativa)
                        .build());
    }
}
