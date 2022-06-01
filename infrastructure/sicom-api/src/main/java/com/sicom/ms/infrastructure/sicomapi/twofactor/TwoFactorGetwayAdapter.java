package com.sicom.ms.infrastructure.sicomapi.twofactor;

import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorGetway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorUserGateway;
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
public class TwoFactorGetwayAdapter implements TwoFactorGetway {
    //@Value("${app.sicom.apibizagi.url}")
    private String baseUrl = "http://127.0.0.1:8080/";


   @Override
    public Mono<GenerateSecretCodeResponse> generateSecretCode(GenerateSecretCodeRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.post()
                .uri("api/two-factor/generate-secret-code")
                .bodyValue(request)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(GenerateSecretCodeResponse.class));
    }

}