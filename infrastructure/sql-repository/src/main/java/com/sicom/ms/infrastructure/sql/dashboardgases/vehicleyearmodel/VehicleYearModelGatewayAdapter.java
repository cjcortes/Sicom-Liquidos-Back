package com.sicom.ms.infrastructure.sql.dashboardgases.vehicleyearmodel;

import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModel;
import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModelFilters;
import com.sicom.ms.domain.model.dashboardgases.vehicleyearmodel.VehicleYearModelGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class VehicleYearModelGatewayAdapter extends BaseGatewayAdapter<VehicleYearModel, VehicleYearModelData, Integer> implements VehicleYearModelGateway {
    private final EntityManager entityManager;

    public VehicleYearModelGatewayAdapter(ObjectConverter<VehicleYearModel, VehicleYearModelData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<VehicleYearModel> getVehicleYearModelByFilters(VehicleYearModelFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getVehicleYearModel.procedure");

        storedProcedureQuery.setParameter("id_modelo_ano_vehiculo", request.getVehicleYearModelId());

        List<VehicleYearModelData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
