package com.sicom.ms.infrastructure.sql.orders;

import com.sicom.ms.domain.model.orders.Order;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.model.orders.OrdersGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class OrdersGatewayAdapter extends BaseGatewayAdapter<Order, OrderData, Integer> implements OrdersGateway {

    private final EntityManager entityManager;

    public OrdersGatewayAdapter(EntityManager entityManager, ObjectConverter<Order, OrderData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<Order> getAllByFilters(OrderFilters orderFilters) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("ordersByFilters.procedure");

        storedProcedureQuery.setParameter("p_vrc_codaut_ope", orderFilters.getAuthCode());
        storedProcedureQuery.setParameter("p_vrc_sicom_age", orderFilters.getClientCode());
        storedProcedureQuery.setParameter("p_vrc_sicom_agp", orderFilters.getProviderPlantCode());
        storedProcedureQuery.setParameter("p_chr_tipped_ope", orderFilters.getOrderType());
        storedProcedureQuery.setParameter("p_fecha_inicio", orderFilters.getSuggestedDeliveryStartDate());
        storedProcedureQuery.setParameter("p_fecha_fin", orderFilters.getSuggestedDeliveryEndDate());

        List<OrderData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}