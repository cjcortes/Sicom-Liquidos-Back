package com.sicom.ms.infrastructure.sql.ordertypes;

import com.sicom.ms.domain.model.ordertypes.OrderType;
import com.sicom.ms.domain.model.ordertypes.OrderTypesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class OrderTypesGatewayAdapter extends BaseGatewayAdapter<OrderType, OrderTypeData, Integer> implements OrderTypesGateway {

    private final EntityManager entityManager;

    public OrderTypesGatewayAdapter(EntityManager entityManager, ObjectConverter<OrderType, OrderTypeData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<OrderType> getAll(String userCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("orderTypes.procedure");

        storedProcedureQuery.setParameter("strUsuario", userCode);

        List<OrderTypeData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
