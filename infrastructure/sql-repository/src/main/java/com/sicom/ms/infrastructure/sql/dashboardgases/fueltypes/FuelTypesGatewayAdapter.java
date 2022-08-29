package com.sicom.ms.infrastructure.sql.dashboardgases.fueltypes;

import com.sicom.ms.domain.model.dashboardgases.fueltypes.FuelType;
import com.sicom.ms.domain.model.dashboardgases.fueltypes.FuelTypesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class FuelTypesGatewayAdapter extends BaseGatewayAdapter<FuelType, FuelTypeData, Integer> implements FuelTypesGateway {

    private final EntityManager entityManager;

    public FuelTypesGatewayAdapter(EntityManager entityManager, ObjectConverter<FuelType, FuelTypeData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }
    @Override
    public Flux<FuelType> getByFuelType(String fuelCode) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getFuelType.procedure");

        storedProcedureQuery.setParameter("cod_combustible", fuelCode);

        List<FuelTypeData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
