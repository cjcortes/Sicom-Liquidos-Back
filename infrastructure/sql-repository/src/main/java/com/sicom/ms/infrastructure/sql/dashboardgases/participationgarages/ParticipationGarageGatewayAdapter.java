package com.sicom.ms.infrastructure.sql.dashboardgases.participationgarages;

import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarage;
import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarageFilters;
import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarageGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ParticipationGarageGatewayAdapter extends BaseGatewayAdapter<ParticipationGarage, ParticipationGarageData, Integer> implements ParticipationGarageGateway {

    private final EntityManager entityManager;

    public ParticipationGarageGatewayAdapter(ObjectConverter<ParticipationGarage, ParticipationGarageData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<ParticipationGarage> getParticipationGarageByFilters(ParticipationGarageFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getParticipationGarage.procedure");

        storedProcedureQuery.setParameter("cod_sicom_certificador", request.getCertifierSicomCode());

        List<ParticipationGarageData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
