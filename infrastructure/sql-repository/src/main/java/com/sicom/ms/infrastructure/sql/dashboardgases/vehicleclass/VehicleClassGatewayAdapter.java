package com.sicom.ms.infrastructure.sql.dashboardgases.vehicleclass;

import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClass;
import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClassFilters;
import com.sicom.ms.domain.model.dashboardgases.vehicleclass.VehicleClassGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class VehicleClassGatewayAdapter extends BaseGatewayAdapter<VehicleClass, VehicleClassData, Integer> implements VehicleClassGateway {

    private final EntityManager entityManager;

    public VehicleClassGatewayAdapter(ObjectConverter<VehicleClass, VehicleClassData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<VehicleClass> getVehicleClassByFilters(VehicleClassFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getVehicleClass.procedure");

        storedProcedureQuery.setParameter("id_clase_vehiculo", request.getVehicleClassId());

        List<VehicleClassData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
