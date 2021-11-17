package com.sicom.ms.infrastructure.sicomapi.plants;

import com.sicom.ms.domain.model.plants.ReceiptPlant;
import com.sicom.ms.domain.model.plants.PlantsGateway;
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
    public Flux<ReceiptPlant> getAllPlants(String agentId) {

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<ReceiptPlantDTO> response = client.get()
                .uri("WEBSERVICE/liquidos/ops/Agente/filtro?codigosicom="+agentId+"")
                .retrieve()
                .bodyToMono(ReceiptPlantDTO.class);

        ReceiptPlantDTO receiptPlantDTO = response.block();
        List<ReceiptPlant> receiptPlants = new ArrayList<>();

        receiptPlantDTO.entities.sCMM_Agente.col_Planta.sCMM_Planta.forEach(value -> {
            receiptPlants.add(ReceiptPlant.builder()
                    .idPlanta(value.iCodigoSICOMPlanta)
                    .nombrePlanta(value.nombrePlanta)
                    .estado(value.sEstado).build());
        });

        return Mono.just(receiptPlants).flatMapMany(Flux::fromIterable);
    }
}
