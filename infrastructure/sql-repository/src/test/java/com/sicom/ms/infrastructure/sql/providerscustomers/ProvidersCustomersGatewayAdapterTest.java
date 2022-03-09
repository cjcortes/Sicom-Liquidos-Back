package com.sicom.ms.infrastructure.sql.providerscustomers;

import com.sicom.ms.domain.model.orderinfo.OrderInfo;
import com.sicom.ms.domain.model.providerscustomers.ProviderCustomer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProvidersCustomersGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private ProviderCustomerConverter providerCustomerConverter;

    @InjectMocks
    private ProvidersCustomersGatewayAdapter providersCustomersGatewayAdapter;

    @Test
    void getByOrderIdShouldReturnProviderCustomerFromDb() {

        var expected = ProviderCustomer.builder().build();

        var dataExpected = Collections.singletonList(new ProviderCustomerData());

        when(entityManager.createNamedStoredProcedureQuery("orderProviderCustomer.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(providerCustomerConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected);

        StepVerifier.create(providersCustomersGatewayAdapter.getByOrderId("1"))
                .expectNext(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("codigo_autorizacion", "1");
    }

    @Test
    void getByOrderIdShouldReturnProviderCustomerEmptyWhenDbReturnEmpty() {

        var dataExpected = Collections.emptyList();

        when(entityManager.createNamedStoredProcedureQuery("orderProviderCustomer.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);

        StepVerifier.create(providersCustomersGatewayAdapter.getByOrderId("1"))
                .assertNext(orderInfo -> assertThat(orderInfo).isInstanceOf(ProviderCustomer.class))
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("codigo_autorizacion", "1");
        verify(providerCustomerConverter, times(0)).toEntity(any());
    }
}
