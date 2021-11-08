package com.sicom.ms.infrastructure.sicomapi.vehicles;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.model.vehicles.Vehicles;
import com.sicom.ms.domain.model.vehicles.VehiclesListGateway;
import com.sicom.ms.infrastructure.sicomapi.plants.PlantDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Repository
public class VehiclesListGatewayAdapter implements VehiclesListGateway {
    //@Value("${app.sicom.api.url}")
    @Value("http://192.168.76.151/")
    private String baseUrl;

    @Override
    public Flux<Vehicles> getVehiclesBySicomAgentId(String agentId) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("WEBSERVICE/liquidos/ops/VehiculosAgente?codigoSicom="+agentId+"")
                .retrieve()
                .bodyToFlux(VehiclesDTO.class).map(v -> Vehicles.builder()
                        .buques(v.buques)
                        .barcazas(v.barcazas)
                        .carroTanques(v.carroTanques)
                        .tractocamiones(v.tractocamiones)
                        .build());
    }
}
