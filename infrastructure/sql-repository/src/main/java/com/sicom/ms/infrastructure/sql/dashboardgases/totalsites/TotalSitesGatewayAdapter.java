package com.sicom.ms.infrastructure.sql.dashboardgases.totalsites;

import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSite;
import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSiteFilters;
import com.sicom.ms.domain.model.dashboardgases.totalsites.TotalSitesGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class TotalSitesGatewayAdapter extends BaseGatewayAdapter<TotalSite, TotalSiteData, Integer> implements TotalSitesGateway {

    private final EntityManager entityManager;

    public TotalSitesGatewayAdapter(EntityManager entityManager, ObjectConverter<TotalSite, TotalSiteData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<TotalSite> getTotalSitesByFilters(TotalSiteFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getTotalSite.procedure");

        storedProcedureQuery.setParameter("cod_departamento", request.getDepartmentCode());
        storedProcedureQuery.setParameter("cod_municipio", request.getCityCode());
        storedProcedureQuery.setParameter("tipo_conversion", request.getConversionType());

        List<TotalSiteData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
