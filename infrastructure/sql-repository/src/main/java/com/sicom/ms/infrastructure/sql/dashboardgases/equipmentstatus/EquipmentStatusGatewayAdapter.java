package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentstatus;

import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatus;
import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatusFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentstatus.EquipmentStatusGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class EquipmentStatusGatewayAdapter extends BaseGatewayAdapter<EquipmentStatus, EquipmentStatusData, Integer> implements EquipmentStatusGateway {

    private final EntityManager entityManager;

    public EquipmentStatusGatewayAdapter(ObjectConverter<EquipmentStatus, EquipmentStatusData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<EquipmentStatus> getEquipmentStatusByFilters(EquipmentStatusFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getEquipmentStatus.procedure");

        storedProcedureQuery.setParameter("id_estado_equipo", request.getEquipmentStatusId());
        storedProcedureQuery.setParameter("id_tipo_equipo", request.getEquipmentTypeId());

        List<EquipmentStatusData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
