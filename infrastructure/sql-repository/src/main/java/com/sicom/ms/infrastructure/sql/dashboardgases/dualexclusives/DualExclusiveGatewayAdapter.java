package com.sicom.ms.infrastructure.sql.dashboardgases.dualexclusives;

import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusive;
import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusiveFilters;
import com.sicom.ms.domain.model.dashboardgases.dualexclusives.DualExclusiveGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class DualExclusiveGatewayAdapter extends BaseGatewayAdapter<DualExclusive, DualExclusiveData, Integer> implements DualExclusiveGateway {

    private final EntityManager entityManager;

    public DualExclusiveGatewayAdapter(ObjectConverter<DualExclusive, DualExclusiveData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<DualExclusive> getDualExlcusivesByFilters(DualExclusiveFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getDualExclusive.procedure");

        storedProcedureQuery.setParameter("id_dual_exclusivo", request.getDualExclusiveId());

        List<DualExclusiveData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
