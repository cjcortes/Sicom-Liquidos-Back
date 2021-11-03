package com.sicom.ms.infrastructure.sql.products;

import com.sicom.ms.domain.model.products.ProductMaster;
import com.sicom.ms.domain.model.products.ProductsMasterGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ProductsMasterGetawayAdapter extends BaseGatewayAdapter<ProductMaster, ProductMasterData, Integer> implements ProductsMasterGateway {
    private final EntityManager entityManager;

    public ProductsMasterGetawayAdapter(EntityManager entityManager, ObjectConverter<ProductMaster, ProductMasterData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<ProductMaster> getAllProducts(String sicomAgent) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("listProducts.procedure");

        storedProcedureQuery.setParameter("p_vrc_codigo_sicom", sicomAgent);

        List<ProductMasterData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }

}