package com.sicom.ms.infrastructure.web.consumptions;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.model.ordertypes.OrderType;
import com.sicom.ms.domain.usecase.consumptions.GetConsumptionUseCase;
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
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;
import static com.sicom.ms.infrastructure.web.TestConstants.PREFIXED_TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;


@WebFluxTest
@ContextConfiguration(classes = {ConsumptionsController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class ConsumptionsControllerTest {

    private static final String YEAR_DESCRIPTION = "Año del consumo";
    private static final String MONTH_DESCRIPTION = "Mes del consumo";
    private static final String ASSIGNED_QUOTA_DESCRIPTION = "Cupo asignado";
    private static final String AVAILABLE_QUOTA_DESCRIPTION = "Cupo disponible";
    private static final String CONSUMPTION_DESCRIPTION = "Consumo del mes";
    private static final String PERCENT_DESCRIPTION = "Porcentaje consumido";

    private static final FieldDescriptor[] ORDER_TYPE_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("year")
                    .type(JsonFieldType.NUMBER)
                    .description(YEAR_DESCRIPTION),
            fieldWithPath("month")
                    .type(JsonFieldType.NUMBER)
                    .description(MONTH_DESCRIPTION),
            fieldWithPath("assignedQuota")
                    .type(JsonFieldType.NUMBER)
                    .description(ASSIGNED_QUOTA_DESCRIPTION),
            fieldWithPath("availableQuota")
                    .type(JsonFieldType.NUMBER)
                    .description(AVAILABLE_QUOTA_DESCRIPTION),
            fieldWithPath("consumption")
                    .type(JsonFieldType.NUMBER)
                    .description(CONSUMPTION_DESCRIPTION),
            fieldWithPath("percent")
                    .type(JsonFieldType.NUMBER)
                    .description(PERCENT_DESCRIPTION)
    };

    @MockBean
    private GetConsumptionUseCase getConsumptionUseCase;

    @MockBean
    private AuthenticationGateway authenticationGateway;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClientFactory.create(applicationContext, restDocumentation);
    }

    @Test
    void getConsumptionShouldReturnConsumptionFromUseCase() {
        var consumption =
                Consumption.builder()
                        .year(2020)
                        .month(8)
                        .assignedQuota(4000)
                        .availableQuota(2000)
                        .consumption(2000)
                        .percent(50)
                        .build();

        Map<String, Object> claims = Map.of(SICOM_AGENT, "1");

        when(getConsumptionUseCase.get("1"))
                .thenReturn(Mono.just(consumption));

        when(authenticationGateway.getClaims(any()))
                .thenReturn(Mono.just(claims));

        webTestClient.get()
                .uri("/api/consumptions")
                .header(HttpHeaders.AUTHORIZATION, PREFIXED_TOKEN)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(OrderType.class)
                .consumeWith(document(
                        "consumptions",
                        responseFields(ORDER_TYPE_DESCRIPTOR)
                ));

        verify(getConsumptionUseCase).get("1");
    }
}
