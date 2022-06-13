package com.sicom.ms.infrastructure.sicomapi.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class TwoFactorGatewayAdapter implements TwoFactorGateway {
    @Value("${app.two-factor.api.url}")
    private String baseUrl;


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
                .exchange()
                .flatMap(response -> response.bodyToMono(GenerateSecretCodeResponse.class));
    }

    @Override
    public Mono<ConfirmSecretCodeResponse> confirmSecretCode(ConfirmSecretCodeRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.post()
                .uri("api/two-factor/confirm-secret-code")
                .bodyValue(request)
                .exchange()
                .flatMap(response -> response.bodyToMono(ConfirmSecretCodeResponse.class));
    }

}
