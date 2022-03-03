package com.sicom.ms.infrastructure.sql.orderinfo;

import com.sicom.ms.domain.model.orderinfo.OrderInfo;
import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.infrastructure.sql.orders.OrderConverter;
import com.sicom.ms.infrastructure.sql.orders.OrderData;
import com.sicom.ms.infrastructure.sql.orders.OrdersGatewayAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderInfoGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private OrderInfoConverter orderInfoConverter;

    @InjectMocks
    private OrderInfoGatewayAdapter orderInfoGatewayAdapter;

    @Test
    void getByOrderIdShouldReturnOrderInfoFromDb() {

        var expected = OrderInfo.builder().build();

        var dataExpected = Collections.singletonList(new OrderInfoData());

        when(entityManager.createNamedStoredProcedureQuery("orderInfo.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(orderInfoConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected);

        StepVerifier.create(orderInfoGatewayAdapter.getByOrderId("1"))
                .expectNext(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("codigo_autorizacion", "1");
    }

    @Test
    void getByOrderIdShouldReturnOrderInfoEmptyWhenDbReturnEmpty() {

        var dataExpected = Collections.emptyList();

        when(entityManager.createNamedStoredProcedureQuery("orderInfo.procedure"))
                .thenReturn(storedProcedureQuery);

        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);

        StepVerifier.create(orderInfoGatewayAdapter.getByOrderId("1"))
                .assertNext(orderInfo -> assertThat(orderInfo).isInstanceOf(OrderInfo.class))
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("codigo_autorizacion", "1");
        verify(orderInfoConverter, times(0)).toEntity(any());
    }
}
