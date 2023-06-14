package com.sicom.ms.infrastructure.sql.dashboardgases.totalgaragerevisions;

import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevision;
import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevisionFilters;
import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevisionsGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class TotalGarageRevisionsGatewayAdapter extends BaseGatewayAdapter<TotalGarageRevision, TotalGarageRevisionData, Integer> implements TotalGarageRevisionsGateway {

    private final EntityManager entityManager;

    public TotalGarageRevisionsGatewayAdapter(ObjectConverter<TotalGarageRevision, TotalGarageRevisionData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<TotalGarageRevision> getTotalGarageRevisionsByFilters(TotalGarageRevisionFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getTotalGarageRevision.procedure");

        storedProcedureQuery.setParameter("cod_sicom_taller", request.getGarageSicomCode());
        storedProcedureQuery.setParameter("fecha_ini_filtro", request.getStartDate());
        storedProcedureQuery.setParameter("fecha_fin_filtro", request.getEndDate());

        List<TotalGarageRevisionData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
