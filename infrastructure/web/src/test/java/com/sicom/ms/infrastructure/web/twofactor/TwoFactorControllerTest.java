package com.sicom.ms.infrastructure.web.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.usecase.twofactor.ConfirmSecretCodeUseCase;
import com.sicom.ms.domain.usecase.twofactor.GenerateSecretCodeUseCase;
import com.sicom.ms.infrastructure.web.WebTestClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = {TwoFactorController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@TestPropertySource(properties = {"app.two-factor.mail.subject=subject", "app.two-factor.mail.body=body"})
class TwoFactorControllerTest {



    @MockBean
    private GenerateSecretCodeUseCase generateSecretCodeUseCase;

    @MockBean
    private ConfirmSecretCodeUseCase confirmSecretCodeUseCase;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClientFactory.create(applicationContext, restDocumentation);
    }



    @Test
    void generateSecretCodeTest() {
        final var request = GenerateSecretCodeRequest.builder().build();
        final var response = GenerateSecretCodeResponse.builder().build();

        when(generateSecretCodeUseCase.generateSecretCode(any(GenerateSecretCodeRequest.class), any(String.class), any(String.class)))
                .thenReturn(Mono.just(response));

        webTestClient.post()
                .uri("/api/two-factor/generate-secret-code")
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(GenerateSecretCodeResponse.class)
                .isEqualTo(response);

        verify(generateSecretCodeUseCase).generateSecretCode(request, "subject", "body");
    }

    @Test
    void confirmSecretCodeTest() {
        final var request = ConfirmSecretCodeRequest.builder().build();
        final var response = ConfirmSecretCodeResponse.builder().build();

        when(confirmSecretCodeUseCase.confirmSecretCode(any(ConfirmSecretCodeRequest.class)))
                .thenReturn(Mono.just(response));

        webTestClient.post()
                .uri("/api/two-factor/confirm-secret-code")
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ConfirmSecretCodeResponse.class)
                .isEqualTo(response);

        verify(confirmSecretCodeUseCase).confirmSecretCode(request);
    }
}