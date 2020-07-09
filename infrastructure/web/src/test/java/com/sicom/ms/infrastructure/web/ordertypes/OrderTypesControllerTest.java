package com.sicom.ms.infrastructure.web.ordertypes;

import com.sicom.ms.domain.model.orders.OrderType;
import com.sicom.ms.domain.usecase.ordertypes.GetAllOrderTypesUseCase;
import com.sicom.ms.infrastructure.web.WebTestClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;

import static com.sicom.ms.infrastructure.web.TestConstants.PREFIXED_TOKEN;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;


@WebFluxTest
@ContextConfiguration(classes = {OrderTypesController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class OrderTypesControllerTest {

    private static final String CODE_DESCRIPTION = "Código del tipo de orden";
    private static final String NAME_DESCRIPTION = "Nombre del tipo de orden";
    private static final String DESCRIPTION_DESCRIPTION = "Descripción del tipo de orden";

    private static final FieldDescriptor[] ORDER_TYPE_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("[].code")
                    .type(JsonFieldType.NUMBER)
                    .description(CODE_DESCRIPTION),
            fieldWithPath("[].name")
                    .type(JsonFieldType.STRING)
                    .description(NAME_DESCRIPTION),
            fieldWithPath("[].description")
                    .type(JsonFieldType.STRING)
                    .description(DESCRIPTION_DESCRIPTION)
    };

    @MockBean
    private GetAllOrderTypesUseCase getAllOrderTypesUseCase;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClientFactory.create(applicationContext, restDocumentation);
    }

    @Test
    void getOrdersTypesShouldReturnOrdersTypesFromUseCase() {
        var ordersTypes = Arrays.asList(
                OrderType.builder()
                        .code(123)
                        .name("Order 1")
                        .description("Descripcion 1")
                        .build(),
                OrderType.builder()
                        .code(456)
                        .name("Order 2")
                        .description("Descripcion 2")
                        .build()
        );

        when(getAllOrderTypesUseCase.getAll(anyInt()))
                .thenReturn(Flux.fromIterable(ordersTypes));

        webTestClient.get()
                .uri("/api/order-types")
                .header(HttpHeaders.AUTHORIZATION, PREFIXED_TOKEN)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(OrderType.class)
                .consumeWith(document(
                        "order-types",
                        responseFields(ORDER_TYPE_DESCRIPTOR)
                ));

        verify(getAllOrderTypesUseCase).getAll(anyInt());
    }
}
