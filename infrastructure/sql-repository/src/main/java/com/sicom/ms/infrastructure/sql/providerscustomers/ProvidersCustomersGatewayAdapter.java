package com.sicom.ms.infrastructure.sql.providerscustomers;

import com.sicom.ms.domain.model.providerscustomers.ProviderCustomer;
import com.sicom.ms.domain.model.providerscustomers.ProvidersCustomersGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ProvidersCustomersGatewayAdapter extends BaseGatewayAdapter<ProviderCustomer, ProviderCustomerData, Integer> implements ProvidersCustomersGateway {
    private final EntityManager entityManager;

    public ProvidersCustomersGatewayAdapter(EntityManager entityManager, ObjectConverter<ProviderCustomer, ProviderCustomerData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Mono<ProviderCustomer> getByOrderId(String orderId) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("orderProviderCustomer.procedure");

        storedProcedureQuery.setParameter("p_vrc_codaut_op", orderId);

        List<ProviderCustomerData> result = storedProcedureQuery.getResultList();

        if (!result.isEmpty()) {
            return Mono.just(toEntity(result.get(0)));
        }

        return Mono.just(ProviderCustomer.builder().build());
    }
}
