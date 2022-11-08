package com.sicom.ms.infrastructure.sicomapi.equipment;

import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentRequest;
import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentResponse;
import com.sicom.ms.domain.model.dashboardgases.equipment.EquipmentsGateway;
import com.sicom.ms.domain.model.error.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class EquipmentGatewayAdapter implements EquipmentsGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;
    @Override
    public Mono<EquipmentResponse> createEquipment(EquipmentRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Mono<EquipmentResponse> result = client.post()
                .uri("WEBSERVICE/GCV_WS/plantillas/registrarEquiposTallerPlantillas")
                .bodyValue(request)
                .exchange()
                .flatMap( clientResponse -> {
                    if ( clientResponse.statusCode() == HttpStatus.BAD_REQUEST ) {
                        return clientResponse.bodyToMono(EquipmentResponse.class)
                                .flatMap(errorDetails ->
                                        Mono.error(new BadRequestException("400", errorDetails.process.processError.errorMessage, null))
                                );
                    }
                    return clientResponse.bodyToMono(EquipmentResponse.class );
                } );

        return result;
    }
}
