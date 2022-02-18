package com.sicom.ms.infrastructure.sicomapi.users;

import com.sicom.ms.domain.model.users.*;
import com.sicom.ms.infrastructure.sicomapi.vehicles.VehiclesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Repository
public class AutenticacionNSGatewayAdapter implements AutenticacionNSGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Mono<User> login(AutenticacionNSRequest request) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

         Mono<AutenticacionNSResponse> mono= client.post()
                .uri("/WEBSERVICE/liquidos/ops/AutenticacionNS")
                .bodyValue(request)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AutenticacionNSResponse.class);

       return mono.flatMap(autenticacionNSResponse -> {
            User userObj = User.builder()
                    .code(autenticacionNSResponse.INT_CODIGO_USR)
                    .user(autenticacionNSResponse.VRC_LOGIN_USR)
                    .name(autenticacionNSResponse.VRC_NOMBRE_USR)
                    .userState(autenticacionNSResponse.CHR_ESTADO_USR)
                    .sicomAgent(autenticacionNSResponse.VRC_SICOM_AGE)
                    .agentSate(autenticacionNSResponse.CHR_ESTADO_AGE)
                    .agentType(autenticacionNSResponse.VRC_NOMBRE_TAG)
                    .profile(autenticacionNSResponse.VRC_NOMBRE_PER)
                    .resultAuth(autenticacionNSResponse.RESULT_AUTH).build();
            return Mono.just(userObj);
        });
    }
}
