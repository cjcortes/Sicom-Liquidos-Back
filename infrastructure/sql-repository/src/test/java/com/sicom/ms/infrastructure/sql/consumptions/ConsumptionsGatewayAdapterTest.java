package com.sicom.ms.infrastructure.sql.consumptions;

import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.model.error.NotFoundException;
import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.infrastructure.sql.users.UserConverter;
import com.sicom.ms.infrastructure.sql.users.UserData;
import com.sicom.ms.infrastructure.sql.users.UsersGatewayAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsumptionsGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private ConsumptionConverter consumptionConverter;

    @InjectMocks
    private ConsumptionsGatewayAdapter consumptionsGatewayAdapter;

    @Test
    void getShouldReturnConsumptionFromDb() {

        var request = "1";
        var expected = Consumption.builder().build();
        var dataExpected = Collections.singletonList(new ConsumptionData());

        when(entityManager.createNamedStoredProcedureQuery("consumption.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(consumptionConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected);

        StepVerifier.create(consumptionsGatewayAdapter.get(request))
                .expectNext(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("p_VRC_SICOM", request);
    }

    @Test
    void loginShouldThrowUnauthorizedWhenDbNotReturnUser() {

        var request = "2";
        var dataExpected = Collections.emptyList();

        when(entityManager.createNamedStoredProcedureQuery("consumption.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);

        assertThatThrownBy(() -> consumptionsGatewayAdapter.get(request))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("consumption not found")
                .hasFieldOrPropertyWithValue("code", "consumption.error.notFound");

        verify(storedProcedureQuery).setParameter("p_VRC_SICOM", request);
    }
//
}
