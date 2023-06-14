package com.sicom.ms.infrastructure.sql.dashboardgases.servicetype;

import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceType;
import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.servicetype.ServiceTypeGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ServiceTypeGatewayAdapter extends BaseGatewayAdapter<ServiceType, ServiceTypeData, Integer> implements ServiceTypeGateway {

    private final EntityManager entityManager;

    public ServiceTypeGatewayAdapter(ObjectConverter<ServiceType, ServiceTypeData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<ServiceType> getServiceTypeByFilters(ServiceTypeFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getServiceType.procedure");

        storedProcedureQuery.setParameter("id_tipo_servicio_vehiculo", request.getVehicleServiceTypeId());

        List<ServiceTypeData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
