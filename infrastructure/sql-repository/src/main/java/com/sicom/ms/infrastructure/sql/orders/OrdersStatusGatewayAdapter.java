package com.sicom.ms.infrastructure.sql.orders;

import com.sicom.ms.domain.model.orders.OrderStatus;
import com.sicom.ms.domain.model.orders.OrderStatusFilters;
import com.sicom.ms.domain.model.orders.OrdersStatusGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class OrdersStatusGatewayAdapter extends BaseGatewayAdapter<OrderStatus, OrderStatusData, Integer> implements OrdersStatusGateway {
    private final EntityManager entityManager;

    public OrdersStatusGatewayAdapter(ObjectConverter<OrderStatus, OrderStatusData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<OrderStatus> getCountOrdersStatus(OrderStatusFilters orderStatusFilters) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("ordersStatus.procedure");

        storedProcedureQuery.setParameter("codigo_sicom", orderStatusFilters.getSicomAgent());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if(orderStatusFilters.getStartDate() != -1) {
            storedProcedureQuery.setParameter("fecha_creacion", dateFormat.format(new Date(orderStatusFilters.getStartDate())));
        }

        if(orderStatusFilters.getEndDate() != -1) {
            storedProcedureQuery.setParameter("fecha_cierre", dateFormat.format(new Date(orderStatusFilters.getEndDate())));
        }

        if(orderStatusFilters.getProduct() != -1) {
            storedProcedureQuery.setParameter("codigo_producto", orderStatusFilters.getProduct());
        }

        if(!orderStatusFilters.getOrderType().equals("-1")) {
            storedProcedureQuery.setParameter("tipo_orden", orderStatusFilters.getOrderType());
        }

        List<OrderStatusData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
