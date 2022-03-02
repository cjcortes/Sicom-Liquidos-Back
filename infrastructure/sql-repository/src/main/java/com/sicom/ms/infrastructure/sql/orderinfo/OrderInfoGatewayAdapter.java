package com.sicom.ms.infrastructure.sql.orderinfo;

import com.sicom.ms.domain.model.orderinfo.OrderInfo;
import com.sicom.ms.domain.model.orderinfo.OrderInfoGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class OrderInfoGatewayAdapter extends BaseGatewayAdapter<OrderInfo, OrderInfoData, Integer> implements OrderInfoGateway {
    private final EntityManager entityManager;

    public OrderInfoGatewayAdapter(EntityManager entityManager, ObjectConverter<OrderInfo, OrderInfoData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Mono<OrderInfo> getByOrderId(String orderId) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("orderInfo.procedure");

        storedProcedureQuery.setParameter("codigo_autorizacion", orderId);

        List<OrderInfoData> result = storedProcedureQuery.getResultList();

        if (!result.isEmpty()) {
            return Mono.just(toEntity(result.get(0)));
        }

        return Mono.just(OrderInfo.builder().build());
    }
}
