package com.sicom.ms.infrastructure.sicomapi.individualrevisioninformation;

import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationFilters;
import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationGateway;
import com.sicom.ms.domain.model.dashboardgases.individualrevisioninformation.IndividualRevisionInformationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class IndividualRevisionInformationGatewayAdapter implements IndividualRevisionInformationGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<IndividualRevisionInformationResponse> getIndividualRevisionInformation(IndividualRevisionInformationFilters filters) {

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
                .uri("WEBSERVICE/GCV_WS/ExtraerInfRevIndividual?" + param + "&numeroCaso=" + filters.getNumeroCaso())
                .retrieve()
                .bodyToFlux(IndividualRevisionInformationResponse.class).map(p -> IndividualRevisionInformationResponse.builder()
                        .value(p.getValue())
                        .error(p.getError())
                        .build());
    }
}
