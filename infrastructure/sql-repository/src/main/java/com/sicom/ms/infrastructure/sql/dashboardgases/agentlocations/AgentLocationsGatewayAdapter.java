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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        List<AgentLocationData> resultConverted = new ArrayList<>();

        for(int i = 0; i < result.size(); i++){
            if((i+1) < result.size()){
                if (result.get(i).getCodeSicomAgent() == result.get(i+1).getCodeSicomAgent()) {
                    result.get(i).setFuelType(result.get(i).getFuelType() + "," + result.get(i+1).getFuelType());
                    result.get(i).setLastDateRegRate(result.get(i).getLastDateRegRate() + "," + result.get(i+1).getLastDateRegRate());
                    result.get(i).setLastRate(result.get(i).getLastRate() + "," + result.get(i+1).getLastRate());
                    result.remove(i+1);
                    resultConverted.add(result.get(i));
                } else {
                    resultConverted.add(result.get(i));
                }
            }

            if(i == (result.size()-1)) {
                resultConverted.add(result.get(i));
            }

        }

        resultConverted = resultConverted.stream().distinct().collect(Collectors.toList());

        return Flux.fromIterable(resultConverted).map(this::toEntity);
    }
}
