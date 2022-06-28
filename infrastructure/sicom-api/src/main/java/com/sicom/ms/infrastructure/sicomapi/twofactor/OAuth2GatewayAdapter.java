package com.sicom.ms.infrastructure.sicomapi.twofactor;

import com.sicom.ms.domain.model.twofactor.RegisterOAuth2PayloadResponse;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Request;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Response;
import com.sicom.ms.domain.model.twofactor.gateway.OAuth2Gateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

@Repository
public class OAuth2GatewayAdapter implements OAuth2Gateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<RegisterOAuth2Response> registerOAuth2(RegisterOAuth2Request request) {
        final var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        final var dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        final var requestDto = RegisterOAuthRequestDTO.builder()
                .codigoAgente(String.valueOf(request.getSicomCode()))
                .resultado(request.getResult())
                .fechaRegistro(dateFormat.format(Date.from(Instant.now())))
                .deviceId(request.getDeviceId())
                .build();

        return client.post()
                .uri("WEBSERVICE/liquidos/app/oAuth2/Registrar2Auth")
                .bodyValue(requestDto)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(RegisterOAuth2ResponseDTO.class))
                .map(dto -> RegisterOAuth2Response.builder()
                        .success(dto.getSuccess())
                        .error(dto.getError())
                        .payload(dto.getPayload() == null
                                ? null
                                : RegisterOAuth2PayloadResponse.builder()
                                .information(dto.getPayload().getInformacion())
                                .date(dto.getPayload().getFecha())
                                .build())
                        .build());
    }
}
