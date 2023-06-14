package com.sicom.ms.infrastructure.sql.dashboardgases.fueltypeparametric;

import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametric;
import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametricFilters;
import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametricGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class FuelTypeParametricGatewayAdapter extends BaseGatewayAdapter<FuelTypeParametric, FuelTypeParametricData, Integer> implements FuelTypeParametricGateway {

    private final EntityManager entityManager;

    public FuelTypeParametricGatewayAdapter(ObjectConverter<FuelTypeParametric, FuelTypeParametricData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<FuelTypeParametric> getFuelTypeParametricByFilters(FuelTypeParametricFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getFuelTypeParametric.procedure");

        storedProcedureQuery.setParameter("id_tipo_combustible", request.getFuelTypeId());

        List<FuelTypeParametricData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
