package com.sicom.ms.infrastructure.sicomapi.users;

import com.sicom.ms.domain.model.forti.FortiUser;
import com.sicom.ms.domain.model.users.EncryptPasswordGateway;
import com.sicom.ms.domain.model.users.EncryptPasswordRequest;
import com.sicom.ms.domain.model.users.EncryptedPasswordResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class EncryptPasswordGatewayAdapter implements EncryptPasswordGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<EncryptedPasswordResponse> encryptPassword(EncryptPasswordRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.post()
                .uri("WEBSERVICE/liquidos/ops/AutenticacionNS/EncriptarPassword")
                .bodyValue(request)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(EncryptedPasswordResponse.class));
    }
}
