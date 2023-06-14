package com.sicom.ms.infrastructure.sql.dashboardgases.revisiontype;

import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionType;
import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.revisiontype.RevisionTypeGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class RevisionTypeGatewayAdapter extends BaseGatewayAdapter<RevisionType, RevisionTypeData, Integer> implements RevisionTypeGateway {

    private final EntityManager entityManager;

    public RevisionTypeGatewayAdapter(ObjectConverter<RevisionType, RevisionTypeData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<RevisionType> getRevisionTypeByFilters(RevisionTypeFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getRevisionType.procedure");

        storedProcedureQuery.setParameter("id_tipo_revision", request.getRevisionTypeId());

        List<RevisionTypeData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
