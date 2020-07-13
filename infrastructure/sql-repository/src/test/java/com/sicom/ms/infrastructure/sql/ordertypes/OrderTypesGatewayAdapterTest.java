package com.sicom.ms.infrastructure.sql.ordertypes;

import com.sicom.ms.domain.model.ordertypes.OrderType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTypesGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private OrderTypeConverter orderTypeConverter;

    @InjectMocks
    private OrderTypesGatewayAdapter orderTypesGatewayAdapter;

    @Test
    void loginShouldReturnUserFromDb() {

        var request = 1;

        var expected = Collections.singletonList(OrderType.builder().build());
        var dataExpected = Collections.singletonList(new OrderTypeData());

        when(entityManager.createNamedStoredProcedureQuery("orderTypes.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(orderTypeConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected.get(0));

        StepVerifier.create(orderTypesGatewayAdapter.getAll(request))
                .expectNextSequence(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("strUsuario", String.valueOf(request));
    }
}
