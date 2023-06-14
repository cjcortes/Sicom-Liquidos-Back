package com.sicom.ms.infrastructure.sicomapi.twofactor;

import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.twofactor.MailRequest;
import com.sicom.ms.domain.model.twofactor.MailResponse;
import com.sicom.ms.domain.model.twofactor.gateway.MailGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Repository
public class MailGatewayAdapter implements MailGateway {

    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Value("${app.two-factor.mail.subject}")
    private String subject;

    @Value("${app.two-factor.mail.body}")
    private String body;

    @Value("${app.two-factor.mail.html}")
    private Boolean html;

    @Value("${app.two-factor.secret-code.timeout}")
    private Integer secretCodeTimeOut;

    @Override
    public Mono<MailResponse> send(MailRequest request, String secretCode) {
        final var mailBody = body.replace("%code", secretCode).replace("%timeout", String.valueOf(secretCodeTimeOut));
        request = request.toBuilder().subject(subject).body(mailBody).html(html).build();

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.post()
                .uri("WEBSERVICE/liquidos/NotificacionCorreo")
                .bodyValue(request)
                .exchange()
                .flatMap(response -> response.statusCode() == HttpStatus.OK
                        ? response.bodyToMono(MailResponse.class)
                        : Mono.error(new BadRequestException("400", "Error sending mail notification", null)));
    }
}
