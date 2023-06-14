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

        result.sort((d1,d2) -> {
            String date1 = d1.name.split("-")[1] + "-" + (Integer.valueOf(d1.name.split("-")[2]) < 10? 0 + d1.name.split("-")[2]:d1.name.split("-")[2]);
            String date2 = d2.name.split("-")[1] + "-" + (Integer.valueOf(d2.name.split("-")[2]) < 10? 0 + d2.name.split("-")[2]:d2.name.split("-")[2]);;

            return date1.compareTo(date2);
        });

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
