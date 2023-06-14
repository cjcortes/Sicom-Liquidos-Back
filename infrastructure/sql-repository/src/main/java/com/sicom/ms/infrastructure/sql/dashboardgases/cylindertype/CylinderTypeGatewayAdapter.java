package com.sicom.ms.infrastructure.sql.dashboardgases.cylindertype;

import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderType;
import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderTypeGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class CylinderTypeGatewayAdapter extends BaseGatewayAdapter<CylinderType, CylinderTypeData, Integer> implements CylinderTypeGateway {

    private final EntityManager entityManager;

    public CylinderTypeGatewayAdapter(ObjectConverter<CylinderType, CylinderTypeData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<CylinderType> getCylinderTypeByFilters(CylinderTypeFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getCylinderType.procedure");

        storedProcedureQuery.setParameter("id_tipo_cilindro", request.getCylinderTypeId());

        List<CylinderTypeData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
