package com.sicom.ms.infrastructure.web.orders;

import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.usecase.orders.GetAllOrdersByFilterUseCase;
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
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.Date;

import static com.sicom.ms.infrastructure.web.TestConstants.PREFIXED_TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest
@ContextConfiguration(classes = {OrdersController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class OrdersControllerTest {

    private static final String AUTH_CODE_DESCRIPTION = "Código de autorización";
    private static final String CLIENT_CODE_DESCRIPTION = "Código SICOM cliente";
    private static final String PROVIDER_PLANT_CODE_DESCRIPTION = "Código SICOM planta proveedor";
    private static final String ORDER_TYPE_DESCRIPTION = "Tipo de orden";
    private static final String ORDER_STATE_DESCRIPTION = "Estado de la orden";
    private static final String SUGGESTED_DELIVERY_START_DATE_DESCRIPTION = "Fecha de inicio de entrega sugerida";
    private static final String SUGGESTED_DELIVERY_END_DATE_DESCRIPTION = "Fecha de finalización de entrega sugerida";

    private static final ParameterDescriptor[] ORDER_FILTERS_DESCRIPTOR = new ParameterDescriptor[]{
            parameterWithName("authCode")
                    .description(AUTH_CODE_DESCRIPTION),
            parameterWithName("clientCode")
                    .description(CLIENT_CODE_DESCRIPTION),
            parameterWithName("providerPlantCode")
                    .description(PROVIDER_PLANT_CODE_DESCRIPTION),
            parameterWithName("orderType")
                    .description(ORDER_TYPE_DESCRIPTION),
            parameterWithName("suggestedDeliveryStartDate")
                    .description(SUGGESTED_DELIVERY_START_DATE_DESCRIPTION),
            parameterWithName("suggestedDeliveryEndDate")
                    .description(SUGGESTED_DELIVERY_END_DATE_DESCRIPTION)
    };

    private static final String AUTHORIZATION_CODE_DESCRIPTION = "Código de autorización";
    private static final String HEAD_PLATE_DESCRIPTION = "Placa del cabezote";
    private static final String TRAILER_DESCRIPTION = "Remolque";
    private static final String TRANSPORT_TYPE_DESCRIPTION = "Tipo de transporte";
    private static final String DRIVER_DESCRIPTION = "Nombre del conductor";
    private static final String DRIVER_IDENTIFICATION_DESCRIPTION = "Cédula del conductor";
    private static final String TRANSPORT_CODE_DESCRIPTION = "Código del transporte";
    private static final String APPLICATION_DATE_DESCRIPTION = "Fecha de la solicitud";
    private static final String DISPATCH_DATE_DESCRIPTION = "Fecha de despacho";

    private static final FieldDescriptor[] ORDER_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("[].authorizationCode")
                    .type(JsonFieldType.STRING)
                    .description(AUTHORIZATION_CODE_DESCRIPTION),
            fieldWithPath("[].headPlate")
                    .type(JsonFieldType.STRING)
                    .description(HEAD_PLATE_DESCRIPTION),
            fieldWithPath("[].trailer")
                    .type(JsonFieldType.STRING)
                    .description(TRAILER_DESCRIPTION),
            fieldWithPath("[].transportType")
                    .type(JsonFieldType.STRING)
                    .description(TRANSPORT_TYPE_DESCRIPTION),
            fieldWithPath("[].driver")
                    .type(JsonFieldType.STRING)
                    .description(DRIVER_DESCRIPTION),
            fieldWithPath("[].driverIdentification")
                    .type(JsonFieldType.STRING)
                    .description(DRIVER_IDENTIFICATION_DESCRIPTION),
            fieldWithPath("[].transportCode")
                    .type(JsonFieldType.NUMBER)
                    .description(TRANSPORT_CODE_DESCRIPTION),
            fieldWithPath("[].applicationDate")
                    .type(JsonFieldType.STRING)
                    .description(APPLICATION_DATE_DESCRIPTION),
            fieldWithPath("[].dispatchDate")
                    .type(JsonFieldType.STRING)
                    .description(DISPATCH_DATE_DESCRIPTION),
            fieldWithPath("[].orderType")
                    .type(JsonFieldType.STRING)
                    .description(ORDER_TYPE_DESCRIPTION)
    };

    @MockBean
    private GetAllOrdersByFilterUseCase getAllOrdersByFilterUseCase;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClientFactory.create(applicationContext, restDocumentation);
    }

    @Test
    void getOrdersByFiltersShouldReturnOrdersFromUseCase() {

        var response = Collections.singletonList(Order.builder()
                .authorizationCode("1")
                .headPlate("ABC-123")
                .trailer("ABC-123")
                .transportType("Transport type")
                .driver("Driver name")
                .driverIdentification("1234567890")
                .transportCode(1)
                .applicationDate(new Date())
                .dispatchDate(new Date())
                .orderType("1")
                .build());

        when(getAllOrdersByFilterUseCase.getAllByFilters(any(OrderFilters.class)))
                .thenReturn(Flux.fromIterable(response));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/orders")
                        .queryParam("authCode", "123")
                        .queryParam("clientCode", "123")
                        .queryParam("providerPlantCode", "123")
                        .queryParam("orderType", "123")
                        .queryParam("suggestedDeliveryStartDate", "1594674713919")
                        .queryParam("suggestedDeliveryEndDate", "1594674713919")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, PREFIXED_TOKEN)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .consumeWith(document("orders",
                        requestParameters(ORDER_FILTERS_DESCRIPTOR),
                        responseFields(ORDER_DESCRIPTOR)
                ));

        verify(getAllOrdersByFilterUseCase).getAllByFilters(any(OrderFilters.class));
    }
}