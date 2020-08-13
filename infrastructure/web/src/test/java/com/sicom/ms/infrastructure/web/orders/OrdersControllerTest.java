package com.sicom.ms.infrastructure.web.orders;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.orderinfo.OrderInfo;
import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderDetail;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.providerscustomers.ProviderCustomer;
import com.sicom.ms.domain.model.vehicles.Vehicle;
import com.sicom.ms.domain.usecase.orders.GetAllOrdersByFilterUseCase;
import com.sicom.ms.domain.usecase.orders.GetOrderUseCase;
import com.sicom.ms.domain.usecase.products.GetProductsByOrderUseCase;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;
import static com.sicom.ms.infrastructure.web.TestConstants.PREFIXED_TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest
@ContextConfiguration(classes = {OrdersController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class OrdersControllerTest extends OrdersControllerFieldsDescriptions {

    @MockBean
    private GetAllOrdersByFilterUseCase getAllOrdersByFilterUseCase;

    @MockBean
    private GetOrderUseCase getOrderUseCase;

    @MockBean
    private GetProductsByOrderUseCase getProductsByOrderUseCase;

    @MockBean
    private AuthenticationGateway authenticationGateway;

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

        Map<String, Object> claims = Map.of(SICOM_AGENT, "1");

        when(authenticationGateway.getClaims(any()))
                .thenReturn(Mono.just(claims));

        when(getAllOrdersByFilterUseCase.getAllByFilters(any(OrderFilters.class)))
                .thenReturn(Flux.fromIterable(response));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/orders")
                        .queryParam("authCode", "123")
                        .queryParam("clientCode", "123")
                        .queryParam("providerPlantCode", "123")
                        .queryParam("orderType", "S")
                        .queryParam("orderState", "1")
                        .queryParam("suggestedDeliveryStartDate", "1594676198112")
                        .queryParam("suggestedDeliveryEndDate", "1594676198112")
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

    @Test
    void getOrderByIdShouldReturnOrderFromUseCase() {

        var response = OrderDetail.builder()
                .orderInfo(OrderInfo.builder()
                        .orderCode(1)
                        .orderType("SIMPLE")
                        .authorizationCode("123")
                        .state("DESPACHADA")
                        .observation("Comentarios")
                        .suggestedDate("DD/MM/YYYY")
                        .billNumber("123")
                        .transportGuide("123")
                        .plantGuide("123")
                        .guideValidity("123")
                        .transportType("Transport type")
                        .borderQuota("CUPO")
                        .nationalQuota("CUPO")
                        .additionalObservation("Comentarios")
                        .build())
                .providerCustomer(ProviderCustomer.builder()
                        .orderCode(1)
                        .customerType("Tipo")
                        .customerSubtype("Subtipo")
                        .customerName("Nombre")
                        .customerSicom("Codigo")
                        .customerNit("NIT")
                        .customerDepartment("Departamento")
                        .customerMunicipality("Municipio")
                        .customerAddress("Dirección")
                        .providerSicom("Codigo")
                        .providerName("Nombre")
                        .providerType("Tipo")
                        .providerSubtype("Subtipo")
                        .providerSicomPlant("Codigo")
                        .providerNamePlant("Nombre")
                        .build())
                .vehicle(Vehicle.builder()
                        .transportType("Tipo")
                        .headPlate("ABC-123")
                        .trailer("ABC-123")
                        .capacity(1)
                        .driver("Nombre")
                        .driverIdentification("123")
                        .compartment(1)
                        .build())
                .build();

        when(getOrderUseCase.getById("1"))
                .thenReturn(Mono.just(response));

        webTestClient.get()
                .uri("/api/orders/{id}", "1")
                .header(HttpHeaders.AUTHORIZATION, PREFIXED_TOKEN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderDetail.class)
                .consumeWith(document("order-by-id",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Código de autorización")
                        ),
                        responseFields(ORDER_DETAIL_DESCRIPTOR)
                ));

        verify(getOrderUseCase).getById("1");
    }

    @Test
    void getProductsByOrderIdShouldReturnProductsFromUseCase() {

        var response = Collections.singletonList(Product.builder()
                .name("Nombre")
                .state("Estado")
                .requestedAmount(123)
                .acceptedAmount(123)
                .dispatchedAmount(123)
                .build());

        when(getProductsByOrderUseCase.getAllByOrderId("1"))
                .thenReturn(Flux.fromIterable(response));

        webTestClient.get()
                .uri("/api/orders/{id}/products", "1")
                .header(HttpHeaders.AUTHORIZATION, PREFIXED_TOKEN)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith(document("order-products",
                        pathParameters(
                                parameterWithName("id")
                                        .description("Código de autorización")
                        ),
                        responseFields(PRODUCT_DESCRIPTOR)
                ));

        verify(getProductsByOrderUseCase).getAllByOrderId("1");
    }
}
