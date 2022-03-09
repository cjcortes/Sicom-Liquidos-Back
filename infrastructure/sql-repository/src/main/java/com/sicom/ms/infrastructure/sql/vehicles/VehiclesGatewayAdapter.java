package com.sicom.ms.infrastructure.sql.vehicles;

import com.sicom.ms.domain.model.vehicles.Vehicle;
import com.sicom.ms.domain.model.vehicles.VehiclesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class VehiclesGatewayAdapter extends BaseGatewayAdapter<Vehicle, VehicleData, Integer> implements VehiclesGateway {

    private final EntityManager entityManager;

    public VehiclesGatewayAdapter(EntityManager entityManager, ObjectConverter<Vehicle, VehicleData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Mono<Vehicle> getByOrderId(String orderId) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("orderVehicle.procedure");

        storedProcedureQuery.setParameter("codigo_autorizacion", orderId);

        List<VehicleData> result = storedProcedureQuery.getResultList();

        if (!result.isEmpty()) {
            return Mono.just(toEntity(result.get(0)));
        }

        return Mono.just(Vehicle.builder().build());
    }
}
