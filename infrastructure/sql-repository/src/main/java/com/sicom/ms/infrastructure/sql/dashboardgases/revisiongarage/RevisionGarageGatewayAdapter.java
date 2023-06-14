package com.sicom.ms.infrastructure.sql.dashboardgases.revisiongarage;

import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarage;
import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarageFilters;
import com.sicom.ms.domain.model.dashboardgases.revisiongarage.RevisionGarageGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class RevisionGarageGatewayAdapter extends BaseGatewayAdapter<RevisionGarage, RevisionGarageData, Integer> implements RevisionGarageGateway {

    private final EntityManager entityManager;

    public RevisionGarageGatewayAdapter(ObjectConverter<RevisionGarage, RevisionGarageData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<RevisionGarage> getRevisionGarageByFilters(RevisionGarageFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getRevisionGarage.procedure");

        storedProcedureQuery.setParameter("id_taller_revision", request.getRevisionGarageId());

        List<RevisionGarageData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
