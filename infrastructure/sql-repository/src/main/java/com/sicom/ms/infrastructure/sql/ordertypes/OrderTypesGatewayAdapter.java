package com.sicom.ms.infrastructure.sql.ordertypes;

import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.orders.OrderType;
import com.sicom.ms.domain.model.orders.OrderTypesGateway;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.model.users.UsersGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import com.sicom.ms.infrastructure.sql.users.UserData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<OrderType> getAll(int userCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("orderTypes.procedure");

        storedProcedureQuery.setParameter("strUsuario", userCode);

        List<OrderTypeData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
