package com.sicom.ms.infrastructure.sql.consumptions;

import com.sicom.ms.domain.model.consumptions.Consumption;
import com.sicom.ms.domain.model.consumptions.ConsumptionsGateway;
import com.sicom.ms.domain.model.error.NotFoundException;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ConsumptionsGatewayAdapter extends BaseGatewayAdapter<Consumption, ConsumptionData, Integer> implements ConsumptionsGateway {

    private final EntityManager entityManager;

    public ConsumptionsGatewayAdapter(EntityManager entityManager, ObjectConverter<Consumption, ConsumptionData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }


    @Override
    public Mono<Consumption> get(String userCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("consumption.procedure");

        storedProcedureQuery.setParameter("p_VRC_SICOM", userCode);

        List result = storedProcedureQuery.getResultList();

        if (result.size() > 0) {
            return Mono.just(toEntity((ConsumptionData) result.get(0)));
        }

        throw new NotFoundException("consumption.error.notFound", "consumption not found");
    }
}
