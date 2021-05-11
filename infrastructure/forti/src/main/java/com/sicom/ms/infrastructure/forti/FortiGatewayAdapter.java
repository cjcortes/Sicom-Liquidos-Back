package com.sicom.ms.infrastructure.forti;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicom.ms.domain.model.forti.FortiGateway;
import com.sicom.ms.domain.model.forti.FortiUser;
import com.sicom.ms.domain.model.forti.ValidateTokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class FortiGatewayAdapter implements FortiGateway {

    @Value("${app.forti.key}")
    private String serviceKey;
    @Value("${app.forti.key}")
    private String user;
    @Value("${app.forti.url}")
    private String baseUrl;

    @Override
    public Mono<FortiUser> searchUser(String userId) {
        ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper(), MediaType.TEXT_HTML));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper(), MediaType.TEXT_HTML));

                }).build();

        var client = WebClient.builder()
                .exchangeStrategies(strategies)
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setBasicAuth(user, serviceKey))
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("localusers/508/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(FortiUser.class));
    }

    @Override
    public Mono<String> validateToken(ValidateTokenRequest validateTokenRequest) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setBasicAuth(user, serviceKey))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.post()
                .uri("auth/")
                .bodyValue(validateTokenRequest)
                .exchange().map(clientResponse -> "OK");
    }
}
