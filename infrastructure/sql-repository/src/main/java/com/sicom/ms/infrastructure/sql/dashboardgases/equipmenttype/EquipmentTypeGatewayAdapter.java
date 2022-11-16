package com.sicom.ms.infrastructure.sql.dashboardgases.equipmenttype;

import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentType;
import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmenttype.EquipmentTypeGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class EquipmentTypeGatewayAdapter extends BaseGatewayAdapter<EquipmentType, EquipmentTypeData, Integer> implements EquipmentTypeGateway {

    private final EntityManager entityManager;

    public EquipmentTypeGatewayAdapter(ObjectConverter<EquipmentType, EquipmentTypeData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<EquipmentType> getEquipmentTypeByFilters(EquipmentTypeFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getEquipmentType.procedure");

        storedProcedureQuery.setParameter("id_tipo_equipo", request.getEquipmentTypeId());

        List<EquipmentTypeData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
