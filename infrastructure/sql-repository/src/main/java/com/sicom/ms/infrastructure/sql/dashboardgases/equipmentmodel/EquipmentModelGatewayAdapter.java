package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentmodel;

import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModel;
import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModelFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentmodel.EquipmentModelGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class EquipmentModelGatewayAdapter extends BaseGatewayAdapter<EquipmentModel, EquipmentModelData, Integer> implements EquipmentModelGateway {
    private final EntityManager entityManager;

    public EquipmentModelGatewayAdapter(ObjectConverter<EquipmentModel, EquipmentModelData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<EquipmentModel> getEquipmentModelsByFilters(EquipmentModelFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getEquipmentModel.procedure");

        storedProcedureQuery.setParameter("id_marca_equipo", request.getEquipmentBrandId());
        storedProcedureQuery.setParameter("id_modelo_equipo", request.getEquipmentModelId());

        List<EquipmentModelData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
