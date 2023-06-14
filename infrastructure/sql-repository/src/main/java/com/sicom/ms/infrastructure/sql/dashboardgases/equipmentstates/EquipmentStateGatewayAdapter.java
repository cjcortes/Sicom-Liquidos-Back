package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentstates;

import com.sicom.ms.domain.model.dashboardgases.equipmentstates.EquipmentState;
import com.sicom.ms.domain.model.dashboardgases.equipmentstates.EquipmentStatesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class EquipmentStateGatewayAdapter extends BaseGatewayAdapter<EquipmentState, EquipmentStateData, Integer> implements EquipmentStatesGateway {

    private final EntityManager entityManager;

    public EquipmentStateGatewayAdapter(ObjectConverter<EquipmentState, EquipmentStateData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<EquipmentState> getEquipmentState() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getEquipmentState.procedure");
        List<EquipmentStateData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
