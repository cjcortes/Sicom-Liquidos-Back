package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.model.orders.OrdersGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Collections;

import static com.sicom.ms.domain.usecase.orders.GetAllOrdersByFilterRules.GET_ORDERS_BY_FILTER_REQUEST_RULES;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllOrdersByFilterUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private OrdersGateway ordersGateway;

    @InjectMocks
    private GetAllOrdersByFilterUseCase getAllOrdersByFilterUseCase;

    @Test
    void getAllByFiltersShouldThrowBadExceptionIfRequestIsInvalid() {
        var request = OrderFilters.builder()
                .authCode("")
                .clientCode("")
                .providerPlantCode("")
                .orderType("")
                .build();

        assertThatThrownBy(() -> getAllOrdersByFilterUseCase.getAllByFilters(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("getAllByFilters bad request")
                .hasFieldOrPropertyWithValue("code", "getAllByFilters.error.badRequest")
                .satisfies(this::checkBadRequestDetails);

        verify(objectValidator).validate(request, GET_ORDERS_BY_FILTER_REQUEST_RULES);
    }

    @Test
    void getAllByFiltersShouldReturnOrdersFromRepository() {
        var request = OrderFilters.builder()
                .authCode("-1")
                .clientCode("-1")
                .providerPlantCode("-1")
                .orderType("-1")
                .build();

        var response = Collections.singletonList(Order.builder().build());

        when(ordersGateway.getAllByFilters(request))
                .thenReturn(Flux.fromIterable(response));

        StepVerifier.create(getAllOrdersByFilterUseCase.getAllByFilters(request))
                .expectNextSequence(response)
                .verifyComplete();

        verify(ordersGateway).getAllByFilters(request);
    }

    private void checkBadRequestDetails(Throwable error) {
        var badRequestException = (BadRequestException) error;

        assertThat(badRequestException.getDetails())
                .extracting(ApplicationErrorDetail::getCode, ApplicationErrorDetail::getMessage)
                .contains(
                        tuple("orderFilters.authCodeCannotBeEmpty",
                                "authCode cannot be empty"),
                        tuple("orderFilters.clientCodeCannotBeEmpty",
                                "clientCode cannot be empty"),
                        tuple("orderFilters.providerPlantCodeCannotBeEmpty",
                                "providerPlantCode cannot be empty"),
                        tuple("orderFilters.orderTypeCannotBeEmpty",
                                "orderType cannot be empty")
                );
    }
}
