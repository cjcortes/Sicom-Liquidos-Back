package com.sicom.ms.infrastructure.sicomapi.validateequipmentstatus;

import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusFilters;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusGateway;
import com.sicom.ms.domain.model.dashboardgases.validateequipmentstatus.ValidateEquipmentStatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Repository
public class ValidateEquipmentStatusGatewayAdapter implements ValidateEquipmentStatusGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<ValidateEquipmentStatusResponse> getValidateEquipmentStatus(ValidateEquipmentStatusFilters request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("WEBSERVICE/GCV_WS/ValidarEstadoEquipo?serial=" + request.getSerial() + "&idTipoEquipo=" + request.getIdTipoEquipo() + "&id_vehiculo_BPM=" + request.getIdVehiculoBPM())
                .retrieve()
                .bodyToFlux(ValidateEquipmentStatusResponse.class).map(p -> ValidateEquipmentStatusResponse.builder()
                        .value(p.getValue())
                        .error(p.getError())
                        .build());
    }
}
