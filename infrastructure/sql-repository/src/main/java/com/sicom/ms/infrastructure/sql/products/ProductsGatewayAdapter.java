package com.sicom.ms.infrastructure.sql.products;

import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.products.ProductsGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ProductsGatewayAdapter extends BaseGatewayAdapter<Product, ProductData, Integer> implements ProductsGateway {
    private final EntityManager entityManager;

    public ProductsGatewayAdapter(EntityManager entityManager, ObjectConverter<Product, ProductData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<Product> getAllByOrderId(String orderId) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("orderProducts.procedure");

        storedProcedureQuery.setParameter("p_vrc_codaut_ope", orderId);

        List<ProductData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
