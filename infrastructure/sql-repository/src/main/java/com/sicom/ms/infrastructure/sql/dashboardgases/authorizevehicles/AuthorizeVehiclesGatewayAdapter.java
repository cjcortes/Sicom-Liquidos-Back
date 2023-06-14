package com.sicom.ms.infrastructure.sql.dashboardgases.authorizevehicles;

import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicle;
import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicleGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class AuthorizeVehiclesGatewayAdapter extends BaseGatewayAdapter<AuthorizeVehicle, AuthorizeVehicleData, Integer> implements AuthorizeVehicleGateway {

    private final EntityManager entityManager;

    public AuthorizeVehiclesGatewayAdapter(ObjectConverter<AuthorizeVehicle, AuthorizeVehicleData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<AuthorizeVehicle> getAuthorizeVehicleByFilters(AuthorizeVehicleFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getAuthorizeVehicle.procedure");

        storedProcedureQuery.setParameter("cod_departamento", request.getDepartmentCode());
        storedProcedureQuery.setParameter("cod_municipio", request.getCityCode());
        storedProcedureQuery.setParameter("cod_sicom_certificador", request.getCertifierSicomCode());

        List<AuthorizeVehicleData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
