package com.sicom.ms.infrastructure.sql.orders;

import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrdersGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private OrderConverter orderConverter;

    @InjectMocks
    private OrdersGatewayAdapter ordersGatewayAdapter;

    @Test
    void getAllByFiltersShouldReturnOrdersFromDb() {

        var request = OrderFilters.builder()
                .sicomAgent("123")
                .authCode("authCode")
                .clientCode("clientCode")
                .providerPlantCode("providerPlantCode")
                .orderType("orderType")
                .orderState(1)
                .suggestedDeliveryStartDate(123)
                .suggestedDeliveryEndDate(123)
                .build();

        var expected = Collections.singletonList(Order.builder().build());

        var dataExpected = Collections.singletonList(new OrderData());

        when(entityManager.createNamedStoredProcedureQuery("ordersByFilters.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(orderConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected.get(0));

        StepVerifier.create(ordersGatewayAdapter.getAllByFilters(request))
                .expectNextSequence(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("p_vrc_codaut_ope", request.getAuthCode());
        verify(storedProcedureQuery).setParameter("p_vrc_sicom_age", request.getClientCode());
        verify(storedProcedureQuery).setParameter("p_vrc_sicom_agp", request.getProviderPlantCode());
        verify(storedProcedureQuery).setParameter("p_chr_tipped_ope", request.getOrderType());
        verify(storedProcedureQuery).setParameter("p_fecha_inicio", new Date(request.getSuggestedDeliveryStartDate()));
        verify(storedProcedureQuery).setParameter("p_fecha_fin", new Date(request.getSuggestedDeliveryEndDate()));
        verify(storedProcedureQuery).setParameter("p_vrc_usuario", request.getSicomAgent());
        verify(storedProcedureQuery).setParameter("p_int_estado", request.getOrderState());
    }
}
