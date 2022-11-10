package com.sicom.ms.infrastructure.sql.dashboardgases.vehiclereference;

import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReference;
import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReferenceFilters;
import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReferenceGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class VehicleReferenceGatewayAdapter extends BaseGatewayAdapter<VehicleReference, VehicleReferenceData, Integer> implements VehicleReferenceGateway {
    private final EntityManager entityManager;

    public VehicleReferenceGatewayAdapter(ObjectConverter<VehicleReference, VehicleReferenceData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<VehicleReference> getVehicleReferenceByFilters(VehicleReferenceFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getVehicleReference.procedure");

        storedProcedureQuery.setParameter("id_referencia_vehiculo", request.getVehicleReferenceId());
        storedProcedureQuery.setParameter("id_marca_vehiculo", request.getVehicleBrandId());
        storedProcedureQuery.setParameter("id_clase_vehiculo", request.getVehicleClassId());
        storedProcedureQuery.setParameter("referencia1", request.getReference1());
        storedProcedureQuery.setParameter("referencia2", request.getReference2());
        storedProcedureQuery.setParameter("referencia3", request.getReference3());

        List<VehicleReferenceData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
