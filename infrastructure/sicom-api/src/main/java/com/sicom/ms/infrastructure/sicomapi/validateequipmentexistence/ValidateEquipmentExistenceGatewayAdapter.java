package com.sicom.ms.infrastructure.sicomapi.validateequipmentexistence;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceFilters;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceGateway;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentexistence.ValidateEquipmentExistenceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Repository
public class ValidateEquipmentExistenceGatewayAdapter implements ValidateEquipmentExistenceGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<ValidateEquipmentExistenceResponse> getValidateEquipmentExistence(ValidateEquipmentExistenceFilters filters) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("WEBSERVICE/GCV_WS/ValidarExistenciaEquipo?serial=" + filters.getSerial() + "&idTipoEquipo=" + filters.getIdTipoEquipo())
                .retrieve()
                .bodyToFlux(ValidateEquipmentExistenceResponse.class).map(p -> ValidateEquipmentExistenceResponse.builder()
                        .value(p.getValue())
                        .error(p.getError())
                        .build());
    }
}
