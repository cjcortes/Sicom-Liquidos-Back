package com.sicom.ms.infrastructure.web;

import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.error.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest
@ContextConfiguration(classes = {
        ApplicationExceptionHandler.class,
        ApplicationExceptionHandlerTest.TestController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class ApplicationExceptionHandlerTest {

    private static final String ERROR_CODE = "context.errorCode";
    private static final String ERROR_MESSAGE = "Error message";
    private static final String REASON_CODE = "context.reasonCode";
    private static final String REASON_MESSAGE = "Reason message";

    private WebTestClient webTestClient;


    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClientFactory.create(applicationContext, restDocumentation);
    }

    @Test
    void shouldReturnBadRequestWithDetails() {
        webTestClient.post()
                .uri("/test")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.code").isEqualTo(ERROR_CODE)
                .jsonPath("$.message").isEqualTo(ERROR_MESSAGE)
                .jsonPath("$.details[0].code").isEqualTo(REASON_CODE)
                .jsonPath("$.details[0].message").isEqualTo(REASON_MESSAGE)
                .consumeWith(document(
                        "bad-request-example",
                        responseFields(
                                fieldWithPath("code")
                                        .type(JsonFieldType.STRING)
                                        .description("Código del error"),
                                fieldWithPath("message")
                                        .type(JsonFieldType.STRING)
                                        .description("Mensaje de descripción del error"),
                                fieldWithPath("details[].code")
                                        .type(JsonFieldType.STRING)
                                        .description("Código de una de las causas del error"),
                                fieldWithPath("details[].message")
                                        .type(JsonFieldType.STRING)
                                        .description("Mensaje de descripción de una de las causas del error")
                        )));
    }

    @Test
    void shouldReturnNotFoundIfNotFoundExceptionThrown() {
        webTestClient.get()
                .uri("/test")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.code").isEqualTo(ERROR_CODE)
                .jsonPath("$.message").isEqualTo(ERROR_MESSAGE)
                .jsonPath("$.details").isEmpty();
    }

    @Test
    void shouldReturnInternalServerErrorIfUnknownExceptionThrown() {
        webTestClient.put()
                .uri("/test")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody()
                .jsonPath("$.code").isEqualTo("internalError")
                .jsonPath("$.message").isEqualTo("Internal error")
                .jsonPath("$.details").isEmpty();
    }

    @RestController
    @RequestMapping("test")
    public static class TestController {

        @PostMapping
        public Mono<Void> badRequestDummy() {
            return Mono.error(new BadRequestException(ERROR_CODE, ERROR_MESSAGE, Collections.singleton(
                    new ApplicationErrorDetail(REASON_CODE, REASON_MESSAGE)
            )));
        }

        @GetMapping
        public Mono<Void> notFoundDummy() {
            return Mono.error(new NotFoundException(ERROR_CODE, ERROR_MESSAGE));
        }

        @PutMapping
        public Mono<Void> internalErrorDummy() {
            return Mono.error(new RuntimeException("unknown error"));
        }

    }

}
