package com.sicom.ms.infrastructure.sql.dashboardgases.totalconvertvehicles;

import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.totalconvertvehicles.TotalConvertVehiclesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class TotalConvertVehiclesGatewayAdapter extends BaseGatewayAdapter<TotalConvertVehicle, TotalConvertVehicleData, Integer> implements TotalConvertVehiclesGateway {

    private final EntityManager entityManager;

    public TotalConvertVehiclesGatewayAdapter(ObjectConverter<TotalConvertVehicle, TotalConvertVehicleData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<TotalConvertVehicle> getTotalConvertVehiclesByFilters(TotalConvertVehicleFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getTotalConvertVehicle.procedure");

        storedProcedureQuery.setParameter("cod_sicom_taller", request.getGarageSicomCode());
        storedProcedureQuery.setParameter("tipo_conversion", request.getConversionType());

        List<TotalConvertVehicleData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
