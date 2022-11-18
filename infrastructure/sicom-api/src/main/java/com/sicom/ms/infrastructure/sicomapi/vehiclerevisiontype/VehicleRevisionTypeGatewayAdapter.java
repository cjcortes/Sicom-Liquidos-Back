package com.sicom.ms.infrastructure.sicomapi.vehiclerevisiontype;

import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeGateway;
import com.sicom.ms.domain.model.dashboardgases.vehiclerevisiontype.VehicleRevisionTypeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Repository
public class VehicleRevisionTypeGatewayAdapter implements VehicleRevisionTypeGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<VehicleRevisionTypeResponse> getVehicleRevisionType(VehicleRevisionTypeFilters filters) {
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
                .build();
        return client.get()
                .uri("WEBSERVICE/GCV_WS/TipoRevisionVehiculoCertificacion?" + param)
                .retrieve()
                .bodyToFlux(VehicleRevisionTypeResponse.class).map(p -> VehicleRevisionTypeResponse.builder()
                        .value(p.getValue())
                        .error(p.getError())
                        .build());
    }
}
