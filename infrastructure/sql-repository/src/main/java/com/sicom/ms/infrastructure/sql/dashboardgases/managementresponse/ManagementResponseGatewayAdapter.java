package com.sicom.ms.infrastructure.sql.dashboardgases.managementresponse;

import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponse;
import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponseFilters;
import com.sicom.ms.domain.model.dashboardgases.managementresponse.ManagementResponseGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ManagementResponseGatewayAdapter extends BaseGatewayAdapter<ManagementResponse, ManagementResponseData, Integer> implements ManagementResponseGateway {

    private final EntityManager entityManager;

    public ManagementResponseGatewayAdapter(ObjectConverter<ManagementResponse, ManagementResponseData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<ManagementResponse> getManagementResponseByFilters(ManagementResponseFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getManagementResponse.procedure");

        storedProcedureQuery.setParameter("id_respuesta_gestion", request.getManagementResponseId());

        List<ManagementResponseData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
