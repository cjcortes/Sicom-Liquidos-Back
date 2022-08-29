package com.sicom.ms.infrastructure.sql.dashboardgases.agentlocations;

import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocation;
import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocationFilters;
import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocationsGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class AgentLocationsGatewayAdapter extends BaseGatewayAdapter<AgentLocation, AgentLocationData, Integer> implements AgentLocationsGateway {

    private final EntityManager entityManager;

    public AgentLocationsGatewayAdapter(EntityManager entityManager, ObjectConverter<AgentLocation, AgentLocationData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<AgentLocation> getAgentLocationsByFilters(AgentLocationFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getAgentLocation.procedure");

        storedProcedureQuery.setParameter("cod_departamento", request.getDepartmentCode());
        storedProcedureQuery.setParameter("cod_municipio", request.getCityCode());
        storedProcedureQuery.setParameter("tipo_gcv", request.getGcvType());

        List<AgentLocationData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
