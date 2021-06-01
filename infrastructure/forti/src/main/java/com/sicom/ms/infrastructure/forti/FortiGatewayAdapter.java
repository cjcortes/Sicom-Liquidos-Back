package com.sicom.ms.infrastructure.forti;
import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.forti.FortiGateway;
import com.sicom.ms.domain.model.forti.FortiUser;
import com.sicom.ms.domain.model.forti.ValidateTokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class FortiGatewayAdapter implements FortiGateway {

    @Value("${app.forti.key}")
    private String serviceKey;
    @Value("${app.forti.user}")
    private String user;
    @Value("${app.forti.url}")
    private String baseUrl;

    @Override
    public Mono<FortiUser> searchUser(int userId) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setBasicAuth(user, serviceKey))
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri("localusers/"+userId+"/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(FortiUser.class));
    }

    @Override
    public Mono<String> validateToken(ValidateTokenRequest validateTokenRequest) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setBasicAuth(user, serviceKey))
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.post()
                .uri("auth/")
                .bodyValue(validateTokenRequest)
                .exchange().map(clientResponse -> {
                    if(clientResponse.statusCode() != HttpStatus.OK) {
                        throw new UnauthorizedException("user.error.invalid", "unauthorized");
                    }
                    return "OK";
                });
    }
}
