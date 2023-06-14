package com.sicom.ms.infrastructure.sql.products;

import com.sicom.ms.domain.model.products.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductsGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private ProductConverter productConverter;

    @InjectMocks
    private ProductsGatewayAdapter productsGatewayAdapter;

    @Test
    void getAllByOrderIdShouldProductsFromDb() {

        var expected = Collections.singletonList(Product.builder().build());

        var dataExpected = Collections.singletonList(new ProductData());

        when(entityManager.createNamedStoredProcedureQuery("orderProducts.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(productConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected.get(0));

        StepVerifier.create(productsGatewayAdapter.getAllByOrderId("1"))
                .expectNextSequence(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("codigo_autorizacion", "1");
    }
}
