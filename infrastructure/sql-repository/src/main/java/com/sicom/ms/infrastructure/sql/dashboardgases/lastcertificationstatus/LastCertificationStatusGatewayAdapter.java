package com.sicom.ms.infrastructure.sql.dashboardgases.lastcertificationstatus;

import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatus;
import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatusFilters;
import com.sicom.ms.domain.model.dashboardgases.lastcertificationstatus.LastCertificationStatusGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class LastCertificationStatusGatewayAdapter extends BaseGatewayAdapter<LastCertificationStatus, LastCertificationStatusData, Integer> implements LastCertificationStatusGateway {

    private final EntityManager entityManager;

    public LastCertificationStatusGatewayAdapter(ObjectConverter<LastCertificationStatus, LastCertificationStatusData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<LastCertificationStatus> getLastCertificationStatusByFilters(LastCertificationStatusFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getLastCertificationStatus.procedure");

        storedProcedureQuery.setParameter("id_estado_ultima_certificacion", request.getLastCertificationStatusId());

        List<LastCertificationStatusData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
