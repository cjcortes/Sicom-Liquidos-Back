package com.sicom.ms.domain.usecase.ordertypes;

import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.orders.OrderType;
import com.sicom.ms.domain.model.orders.OrderTypesGateway;
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
import java.util.List;

import static com.sicom.ms.domain.usecase.ordertypes.GetAllOrderTypesRules.GET_ORDERS_TYPES_REQUEST_RULES;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllOrderTypesUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private OrderTypesGateway orderTypesGateway;

    @InjectMocks
    private GetAllOrderTypesUseCase getAllOrderTypesUseCase;

    @Test
    void getAllShouldThrowBadExceptionIfRequestIsInvalid() {
        var request = -1;

        assertThatThrownBy(() -> getAllOrderTypesUseCase.getAll(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("getAll bad request")
                .hasFieldOrPropertyWithValue("code", "getAll.error.badRequest")
                .satisfies(this::checkBadRequestDetails);

        verify(objectValidator).validate(request, GET_ORDERS_TYPES_REQUEST_RULES);
    }

    @Test
    void getALlShouldReturnOrderTypesFromRepository() {
        var request = 1;
        List<OrderType> response = Collections.emptyList();

        when(orderTypesGateway.getAll(request))
                .thenReturn(Flux.fromIterable(response));

        StepVerifier.create(getAllOrderTypesUseCase.getAll(request))
                .expectNextSequence(response)
                .verifyComplete();

        verify(orderTypesGateway).getAll(request);
    }

    private void checkBadRequestDetails(Throwable error) {
        var badRequestException = (BadRequestException) error;

        assertThat(badRequestException.getDetails())
                .extracting(ApplicationErrorDetail::getCode, ApplicationErrorDetail::getMessage)
                .contains(tuple("userCode.userCodeCannotBeNegative",
                        "userCode cannot be negative"));
    }
}
