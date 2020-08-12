package com.sicom.ms.domain.usecase.consumptions;

import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.model.consumptions.ConsumptionsGateway;
import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.ordertypes.OrderType;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

import static com.sicom.ms.domain.usecase.consumptions.GetConsumptionRules.GET_CONSUMPTION_RULES;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetConsumptionUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private ConsumptionsGateway consumptionsGateway;

    @InjectMocks
    private GetConsumptionUseCase getConsumptionUseCase;

    @Test
    void getAllShouldThrowBadExceptionIfRequestIsInvalid() {

        assertThatThrownBy(() -> getConsumptionUseCase.get(null))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("get bad request")
                .hasFieldOrPropertyWithValue("code", "get.error.badRequest")
                .satisfies(this::checkBadRequestDetails);

        verify(objectValidator).validate(null, GET_CONSUMPTION_RULES);
    }

    @Test
    void getALlShouldReturnOrderTypesFromRepository() {
        var request = "1";
        Consumption response = Consumption.builder().build();

        when(consumptionsGateway.get(request))
                .thenReturn(Mono.just(response));

        StepVerifier.create(getConsumptionUseCase.get(request))
                .expectNext(response)
                .verifyComplete();

        verify(consumptionsGateway).get(request);
    }

    private void checkBadRequestDetails(Throwable error) {
        var badRequestException = (BadRequestException) error;

        assertThat(badRequestException.getDetails())
                .extracting(ApplicationErrorDetail::getCode, ApplicationErrorDetail::getMessage)
                .contains(tuple("sicomAgent.sicomAgentCannotBeNull",
                        "sicomAgent cannot be null"));
    }
}
