package com.sicom.ms.infrastructure.web.fcm;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.usecase.fcm.NotificationUseCase;
import com.sicom.ms.domain.usecase.fcm.SendPushNotificationUseCase;
import com.sicom.ms.infrastructure.web.WebTestClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest
@ContextConfiguration(classes = {FCMController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class FCMControllerTest {

    private static final String TITLE_DESCRIPTION = "Titulo de la notificación push";
    private static final String BODY_DESCRIPTION = "Descripción de la notificación push";
    private static final String DUE_DATE = "Fecha de vencimiento de la notificación";
    private static final String FILE_URL = "url del documento adjunto en la notificación";
    private static final String FILE_NAME = "nombre del documento adjunto en la notificación";

    private static final FieldDescriptor[] NOTIFICATION_REQUEST_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("title")
                    .type(JsonFieldType.STRING)
                    .description(TITLE_DESCRIPTION),
            fieldWithPath("body")
                    .type(JsonFieldType.STRING)
                    .description(BODY_DESCRIPTION),
            fieldWithPath("dueDate")
                    .type(JsonFieldType.NUMBER)
                    .description(DUE_DATE),
            fieldWithPath("fileUrl")
                    .type(JsonFieldType.STRING)
                    .description(FILE_URL),
            fieldWithPath("fileName")
                    .type(JsonFieldType.STRING)
                    .description(FILE_NAME)

    };

    @MockBean
    private SendPushNotificationUseCase sendPushNotificationUseCase;

    @MockBean
    private NotificationUseCase notificationUseCase;

    @MockBean
    private AuthenticationGateway authenticationGateway;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClientFactory.create(applicationContext, restDocumentation);
    }

    @Test
    void sendPushNotificationShouldSendPush() {
        var request = Notification.builder()
                .title("Title")
                .body("Body")
                .dueDate(new Date())
                .fileName("File Name")
                .fileUrl("File Url")
                .user("user")
                .build();

        when(sendPushNotificationUseCase.send(request))
                .thenReturn(Mono.just("OK"));

        webTestClient.post()
                .uri("/api/fcm")
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("OK")
                .consumeWith(document(
                        "fcm-send",
                        requestFields(NOTIFICATION_REQUEST_DESCRIPTOR)
                ));
        verify(sendPushNotificationUseCase).send(request);
    }
}
