package com.sicom.ms.infrastructure.sql.dashboardgases.dedicateconvertvehicle;

import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicle;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicleGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class DedicateConvertVehicleGatewayAdapter extends BaseGatewayAdapter<DedicateConvertVehicle, DedicateConvertVehicleData, Integer> implements DedicateConvertVehicleGateway {
    private final EntityManager entityManager;

    public DedicateConvertVehicleGatewayAdapter(ObjectConverter<DedicateConvertVehicle, DedicateConvertVehicleData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<DedicateConvertVehicle> getDedicateConvertVehiclesByFilters(DedicateConvertVehicleFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getDedicateConvertVehicle.procedure");

        storedProcedureQuery.setParameter("id_convertido_dedicado", request.getDedicateConvertId());

        List<DedicateConvertVehicleData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
