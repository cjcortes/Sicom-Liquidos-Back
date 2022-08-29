package com.sicom.ms.infrastructure.sql.dashboardgases.gcvfuelprices;

import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPrice;
import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPriceFilters;
import com.sicom.ms.domain.model.dashboardgases.gcvfuelprices.GcvFuelPricesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class GcvFuelPricesGatewayAdapter extends BaseGatewayAdapter<GcvFuelPrice, GcvFuelPriceData, Integer> implements GcvFuelPricesGateway {

    private final EntityManager entityManager;

    public GcvFuelPricesGatewayAdapter(EntityManager entityManager, ObjectConverter<GcvFuelPrice, GcvFuelPriceData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<GcvFuelPrice> getGCVFuelPriceByFilters(GcvFuelPriceFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getGCVFuelPrice.procedure");

        storedProcedureQuery.setParameter("cod_departamento", request.getDepartmentCode());
        storedProcedureQuery.setParameter("cod_municipio", request.getCityCode());
        storedProcedureQuery.setParameter("tipo_combustible", request.getFuelType());

        List<GcvFuelPriceData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
