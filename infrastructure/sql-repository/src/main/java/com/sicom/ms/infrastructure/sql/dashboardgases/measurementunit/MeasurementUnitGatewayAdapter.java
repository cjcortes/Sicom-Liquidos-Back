package com.sicom.ms.infrastructure.sql.dashboardgases.measurementunit;

import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnit;
import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnitFilters;
import com.sicom.ms.domain.model.dashboardgases.measurementunit.MeasurementUnitGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class MeasurementUnitGatewayAdapter extends BaseGatewayAdapter<MeasurementUnit, MeasurementUnitData, Integer> implements MeasurementUnitGateway {

    private final EntityManager entityManager;

    public MeasurementUnitGatewayAdapter(ObjectConverter<MeasurementUnit, MeasurementUnitData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<MeasurementUnit> getMeasurementUnitByFilters(MeasurementUnitFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getMeasurementUnit.procedure");

        storedProcedureQuery.setParameter("id_unidad_medida", request.getMeasurementUnitId());

        List<MeasurementUnitData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
