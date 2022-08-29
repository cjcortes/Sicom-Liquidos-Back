package com.sicom.ms.infrastructure.sql.dashboardgases.departmentscities;

import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCity;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentCityFilters;
import com.sicom.ms.domain.model.dashboardgases.departmentscities.DepartmentsCitiesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class DepartmentsCitiesGatewayAdapter extends BaseGatewayAdapter<DepartmentCity, DepartmentCityData, Integer> implements DepartmentsCitiesGateway {

    private final EntityManager entityManager;

    public DepartmentsCitiesGatewayAdapter(EntityManager entityManager, ObjectConverter<DepartmentCity, DepartmentCityData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<DepartmentCity> getDepartmentCity(DepartmentCityFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getDepartmentCity.procedure");

        storedProcedureQuery.setParameter("cod_municipio", request.getCityCode());
        storedProcedureQuery.setParameter("cod_departamento", request.getDepartmentCode());

        List<DepartmentCityData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
